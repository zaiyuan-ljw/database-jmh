package icu.wwj.jmh.shardingsphere5;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.concurrent.ThreadLocalRandom;

@State(Scope.Group)
@Fork(3)
@Warmup(iterations = 10, time = 3)
@Measurement(iterations = 10, time = 3)
public class MultiShardingFullPointSelectBenchmark {
    
    private static final int TABLE_SIZE = 100_000;
    
    private final PreparedStatement[] preparedStatements = new PreparedStatement[10];
    
    private final DataSource dataSource = ShardingSpheres.createDataSource("/shardingsphere-jdbc/sysbench.yaml");
    
    private Connection connection;
    
    @Setup(Level.Iteration)
    public void setup() throws Exception {
        connection = dataSource.getConnection();
        for (int i = 0; i < preparedStatements.length; i++) {
            preparedStatements[i] = connection.prepareStatement("select c from sbtest" + (i + 1) + " where id = ?");
        }
    }
    
    @Group("FullPointSelect")
    @Benchmark
    @OperationsPerInvocation(10)
    public void benchFullPointSelect() throws Exception {
        for (PreparedStatement each : preparedStatements) {
            each.setInt(1, ThreadLocalRandom.current().nextInt(TABLE_SIZE));
            each.execute();
        }
    }
    
    @TearDown(Level.Iteration)
    public void tearDown() throws Exception {
        for (PreparedStatement each : preparedStatements) {
            each.close();
        }
        connection.close();
    }
}
