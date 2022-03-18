package com.sphereex.jmh.shardingsphere4;

import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@State(Scope.Group)
@Fork(3)
@Warmup(iterations = 10, time = 3)
@Measurement(iterations = 10, time = 3)
public class MultiShardingBenchmark {
    
    private static final int TABLE_SIZE = 10_000_000;
    
    private DataSource dataSource;
    
    private Connection connection;
    
    private PreparedStatement preparedStatement;
    
    public MultiShardingBenchmark() {
        try {
            dataSource = YamlShardingDataSourceFactory.createDataSource(new File(getClass().getResource("/sharding-jdbc/sysbench-multi.yaml").getFile()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Setup(Level.Iteration)
    public void setup() throws Exception {
        connection = dataSource.getConnection();
        preparedStatement = connection.prepareStatement("select c from sbtest1 where id = ?");
    }
    
    @Group
    @Benchmark
    public void benchSingleSharding() throws Exception {
        preparedStatement.setInt(1, ((Random) ThreadLocalRandom.current()).nextInt(TABLE_SIZE));
        preparedStatement.execute();
    }
    
    @TearDown(Level.Iteration)
    public void tearDown() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
