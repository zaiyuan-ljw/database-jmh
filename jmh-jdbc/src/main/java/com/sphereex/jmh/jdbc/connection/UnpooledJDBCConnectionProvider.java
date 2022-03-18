package com.sphereex.jmh.jdbc.connection;

import com.sphereex.jmh.jdbc.JDBCConnectionProvider;

import java.sql.Connection;

public interface UnpooledJDBCConnectionProvider extends JDBCConnectionProvider {
    
    @Override
    default Connection getConnection() {
        return Jdbcs.getConnection();
    }
}
