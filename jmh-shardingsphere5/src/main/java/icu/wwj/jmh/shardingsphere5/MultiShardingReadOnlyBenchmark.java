package icu.wwj.jmh.shardingsphere5;

import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
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
public class MultiShardingReadOnlyBenchmark {
    
    private final Random random = ThreadLocalRandom.current();
    
    private DataSource dataSource;
    
    private Connection connection;
    
    private final PreparedStatement[] preparedStatements = new PreparedStatement[10];
    
    public MultiShardingReadOnlyBenchmark() {
        try {
            dataSource = YamlShardingSphereDataSourceFactory.createDataSource(new File(getClass().getResource("/shardingsphere-jdbc/sysbench.yaml").getFile()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Setup(Level.Iteration)
    public void setup() throws Exception {
        connection = dataSource.getConnection();
        for (int i = 0; i < preparedStatements.length; i++) {
            preparedStatements[i] = connection.prepareStatement("select c from sbtest" + (i + 1) + " where id = ?");
        }
    }
    
    @Group("ReadOnlyWithTransaction")
    @Benchmark
    public void benchMultiShardingReadOnly() throws Exception {
        connection.setAutoCommit(false);
        for (PreparedStatement each : preparedStatements) {
            each.setInt(1, random.nextInt(100000));
            each.execute();
        }
        connection.commit();
    }
    
    @TearDown(Level.Iteration)
    public void tearDown() throws Exception {
        for (PreparedStatement each : preparedStatements) {
            each.close();
        }
        connection.close();
    }
}
