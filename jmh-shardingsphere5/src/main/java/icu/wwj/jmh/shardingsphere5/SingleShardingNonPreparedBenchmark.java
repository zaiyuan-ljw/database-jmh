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
import java.sql.Statement;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@State(Scope.Group)
@Fork(3)
@Warmup(iterations = 10, time = 3)
@Measurement(iterations = 10, time = 3)
public class SingleShardingNonPreparedBenchmark {
    
    private final Random random = ThreadLocalRandom.current();
    
    private DataSource dataSource;
    
    private Connection connection;
    
    private Statement statement;
    
    public SingleShardingNonPreparedBenchmark() {
        try {
            dataSource = YamlShardingSphereDataSourceFactory.createDataSource(new File(getClass().getResource("/shardingsphere-jdbc/sysbench-single.yaml").getFile()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Setup(Level.Iteration)
    public void setup() throws Exception {
        connection = dataSource.getConnection();
        statement = connection.createStatement();
    }
    
    @Group
    @Benchmark
    public boolean benchSingleSharding() throws Exception {
        return statement.execute("select c from sbtest1 where id = " + random.nextInt(100000));
    }
    
    @TearDown(Level.Iteration)
    public void tearDown() throws Exception {
        connection.close();
    }
}
