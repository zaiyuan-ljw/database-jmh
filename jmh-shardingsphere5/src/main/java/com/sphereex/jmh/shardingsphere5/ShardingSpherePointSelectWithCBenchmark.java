package com.sphereex.jmh.shardingsphere5;

import com.sphereex.jmh.jdbc.UnpooledPointSelectWithCBenchmarkBase;
import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.sql.Connection;

public class ShardingSpherePointSelectWithCBenchmark extends UnpooledPointSelectWithCBenchmarkBase {
    
    private static final DataSource DATA_SOURCE;
    
    static {
        DATA_SOURCE = ShardingSpheres.createDataSource();
    }
    
    @SneakyThrows
    @Override
    public Connection getConnection() {
        return DATA_SOURCE.getConnection();
    }
}
