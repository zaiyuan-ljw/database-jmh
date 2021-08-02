package icu.wwj.jmh.shardingsphere5;

import org.apache.shardingsphere.sharding.algorithm.sharding.inline.InlineShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Warmup;

import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

@Fork(1)
@Warmup(iterations = 5, time = 3)
@Measurement(iterations = 5, time = 3)
public class InlineShardingAlgorithmBenchmark {
    
    @Benchmark
    public String bench() {
        InlineShardingAlgorithm algorithm = new InlineShardingAlgorithm();
        algorithm.getProps().setProperty("algorithm-expression", "${id % 2}");
        return algorithm.doSharding(Collections.emptyList(), new PreciseShardingValue<>("", "id", ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE)));
    }
    
    @Benchmark
    public String benchDirect() {
        PreciseShardingValue<Integer> value = new PreciseShardingValue<>("", "id", ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE));
        return String.valueOf(value.getValue() % 2);
    }
}
