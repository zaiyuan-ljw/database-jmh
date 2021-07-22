package icu.wwj.jmh.shardingsphere4;

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
import java.util.concurrent.ThreadLocalRandom;

@State(Scope.Group)
@Fork(3)
@Warmup(iterations = 10, time = 3)
@Measurement(iterations = 10, time = 3)
public class MasterSlaveFullPointSelectBenchmark {
    
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    
    private DataSource dataSource;
    
    private Connection connection;
    
    private PreparedStatement[] preparedStatements = new PreparedStatement[10];
    
    public MasterSlaveFullPointSelectBenchmark() {
        try {
            dataSource = YamlMasterSlaveDataSourceFactory.createDataSource(new File(getClass().getResource("/sharding-jdbc/master-slave.yaml").getFile()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Setup(Level.Iteration)
    public void setup() throws Exception {
        connection = dataSource.getConnection();
        for (int i = 0; i < preparedStatements.length; i++) {
            preparedStatements[i] = connection.prepareStatement(String.format("select c from sbtest%d where id = ?", i + 1));
        }
    }
    
    @Group
    @Benchmark
    public void benchMasterSlave() throws Exception {
        PreparedStatement preparedStatement = preparedStatements[random.nextInt(preparedStatements.length)];
        preparedStatement.setInt(1, random.nextInt(100000));
        preparedStatement.execute();
    }
    
    @TearDown(Level.Iteration)
    public void tearDown() throws Exception {
        for (PreparedStatement each : preparedStatements) {
            each.close();
        }
        connection.close();
    }
}
