package com.sphereex.jmh.shardingsphere3;

import com.sphereex.jmh.jdbc.UnpooledPointSelectBenchmarkBase;
import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.sql.Connection;

public class PointSelectBenchmarkShardingSphere3 extends UnpooledPointSelectBenchmarkBase {
    
    private static final DataSource DATA_SOURCE = ShardingSpheres.createDataSource("/sharding-jdbc/sysbench-remote-single.yaml");
    
    @Override
    @SneakyThrows
    public Connection getConnection() {
        return DATA_SOURCE.getConnection();
    }
}
