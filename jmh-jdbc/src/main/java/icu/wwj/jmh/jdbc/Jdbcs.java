package icu.wwj.jmh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public final class Jdbcs {
    
    private Jdbcs() {
        throw new RuntimeException();
    }
    
    public static Connection getConnection() {
        return getConnection(System.getProperty("profile"));
    }
    
    public static Connection getConnection(final String profile) {
        if (null == profile || profile.trim().isEmpty()) {
            throw new RuntimeException("Please specify -Dprofile");
        }
        Properties props = new Properties();
        String path = String.format("/META-INF/profiles/%s.properties", profile);
        try {
            props.load(Jdbcs.class.getResourceAsStream(path));
        } catch (Exception e) {
            throw new RuntimeException(String.format("Fail to get %s", path), e);
        }
        try {
            return DriverManager.getConnection(props.getProperty("jdbc-url"), props.getProperty("username"), props.getProperty("password"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
