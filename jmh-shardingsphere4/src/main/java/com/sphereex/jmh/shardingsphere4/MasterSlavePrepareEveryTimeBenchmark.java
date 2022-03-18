package com.sphereex.jmh.shardingsphere4;

import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlMasterSlaveDataSourceFactory;
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

@State(Scope.Group)
@Fork(3)
@Warmup(iterations = 10, time = 3)
@Measurement(iterations = 10, time = 3)
public class MasterSlavePrepareEveryTimeBenchmark {
    
    private final Random random = new Random();
    
    private DataSource dataSource;
    
    private Connection connection;
    
    public MasterSlavePrepareEveryTimeBenchmark() {
        try {
            dataSource = YamlMasterSlaveDataSourceFactory.createDataSource(new File(getClass().getResource("/sharding-jdbc/master-slave.yaml").getFile()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Setup(Level.Iteration)
    public void setup() throws Exception {
        connection = dataSource.getConnection();
    }
    
    @Group
    @Benchmark
    public void benchMasterSlave() throws Exception {
        try (PreparedStatement preparedStatement = connection.prepareStatement("select c from sbtest1 where id = ?")) {
            preparedStatement.setInt(1, random.nextInt(100000));
            preparedStatement.execute();
        }
    }
    
    @TearDown(Level.Iteration)
    public void tearDown() throws Exception {
        connection.close();
    }
}
