package com.sphereex.jmh.shardingsphere3;

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@State(Scope.Group)
@Fork(3)
@Warmup(iterations = 10, time = 3)
@Measurement(iterations = 10, time = 3)
public class MultiShardingBenchmark {
    
    private final Random random = ThreadLocalRandom.current();
    
    private final DataSource dataSource = ShardingSpheres.createDataSource("/sharding-jdbc/sysbench-multi.yaml");
    
    private Connection connection;
    
    private PreparedStatement preparedStatement;
    
    @Setup(Level.Iteration)
    public void setup() throws Exception {
        connection = dataSource.getConnection();
        preparedStatement = connection.prepareStatement("select c from sbtest1 where id = ?");
    }
    
    @Group
    @Benchmark
    public void benchSingleSharding() throws Exception {
        preparedStatement.setInt(1, random.nextInt(100000));
        preparedStatement.execute();
    }
    
    @TearDown(Level.Iteration)
    public void tearDown() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
