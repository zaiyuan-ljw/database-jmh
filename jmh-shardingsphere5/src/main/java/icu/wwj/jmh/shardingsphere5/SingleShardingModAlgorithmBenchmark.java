package icu.wwj.jmh.shardingsphere5;

import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
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
import java.util.Collection;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@State(Scope.Group)
@Fork(3)
@Warmup(iterations = 10, time = 3)
@Measurement(iterations = 10, time = 3)
public class SingleShardingModAlgorithmBenchmark {
    
    private final Random random = ThreadLocalRandom.current();
    
    private DataSource dataSource;
    
    private Connection connection;
    
    private PreparedStatement preparedStatement;
    
    public SingleShardingModAlgorithmBenchmark() {
        try {
            dataSource = YamlShardingSphereDataSourceFactory.createDataSource(new File(getClass().getResource("/shardingsphere-jdbc/sysbench-single-mod.yaml").getFile()));
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
        preparedStatement.setInt(1, random.nextInt(100000));
        preparedStatement.execute();
    }
    
    @TearDown(Level.Iteration)
    public void tearDown() throws Exception {
        preparedStatement.close();
        connection.close();
    }
    
    public static class IntegerModShardingAlgorithm implements StandardShardingAlgorithm<Integer> {
        
        private String prefix;
        
        private int modValue;
        
        @Override
        public void setProps(final Properties props) {
            prefix = props.getProperty("prefix");
            modValue = Integer.parseInt(props.getProperty("mod"));
        }
        
        @Override
        public String doSharding(final Collection<String> availableTargetNames, final PreciseShardingValue<Integer> shardingValue) {
            return prefix + shardingValue.getValue() % modValue;
        }
        
        @Override
        public Collection<String> doSharding(final Collection<String> availableTargetNames, final RangeShardingValue<Integer> shardingValue) {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public void init() {
        }
        
        @Override
        public String getType() {
            return "MOD_INTEGER";
        }
    }
}
