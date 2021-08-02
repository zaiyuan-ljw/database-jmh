package icu.wwj.jmh.dangling;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Threads(12 * 6)
@Fork(1)
@Warmup(iterations = 5, time = 3)
@Measurement(iterations = 5, time = 3)
@State(Scope.Benchmark)
public class ConcurrentPropertiesBenchmark {
    
    private static final Properties PROPERTIES = new Properties();
    
    private static final Map<String, String> MAP = new HashMap<>();
    
    static {
        PROPERTIES.setProperty("algorithm-expression", "${id % 2}");
        MAP.put("algorithm-expression", "${id % 2}");
    }
    
    @Benchmark
    public String benchGetProps() {
        return PROPERTIES.getProperty("algorithm-expression");
    }
    
    @Benchmark
    public String benchGetMap() {
        return MAP.get("algorithm-expression");
    }
}
