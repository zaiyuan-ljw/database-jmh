package icu.wwj.jmh.shardingsphere5;

import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public final class ShardingSpheres {
    
    public static DataSource createDataSource(final String path) {
        try {
            return YamlShardingSphereDataSourceFactory.createDataSource(new File(ShardingSpheres.class.getResource(path).getFile()));
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
