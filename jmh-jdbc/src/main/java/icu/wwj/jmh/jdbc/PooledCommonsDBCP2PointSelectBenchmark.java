package icu.wwj.jmh.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
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

@State(Scope.Group)
@Fork(3)
@Warmup(iterations = 10, time = 1)
@Measurement(iterations = 10, time = 1)
public class PooledCommonsDBCP2PointSelectBenchmark {
    
    private final Random random = new Random();
    
    private final DataSource dataSource;
    
    private Connection connection;
    
    private PreparedStatement preparedStatement;
    
    public PooledCommonsDBCP2PointSelectBenchmark() {
        BasicDataSource result = new BasicDataSource();
        result.setUrl("jdbc:mysql://localhost:3306/sbtest_direct?useSSL=false&useServerPrepStmts=true&cachePrepStmts=true");
        result.setUsername("root");
        result.setPassword("");
        result.setMaxIdle(12);
        result.setMinIdle(1);
        dataSource = result;
    }
    
    @Setup(Level.Iteration)
    public void setup() throws Exception {
        connection = dataSource.getConnection();
        preparedStatement = connection.prepareStatement("select c from sbtest1 where id = ?");
    }
    
    @Group
    @Benchmark
    public void testMethod() throws Exception {
        preparedStatement.setInt(1, random.nextInt(100000));
        preparedStatement.execute();
    }
    
    @TearDown(Level.Iteration)
    public void tearDown() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
