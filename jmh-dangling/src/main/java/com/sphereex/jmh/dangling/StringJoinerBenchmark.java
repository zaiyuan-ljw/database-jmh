package com.sphereex.jmh.dangling;

import com.google.common.base.Joiner;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Warmup;

import java.util.StringJoiner;

@Fork(1)
@Measurement(iterations = 5, time = 3)
@Warmup(iterations = 5, time = 3)
public class StringJoinerBenchmark {
    
    @Benchmark
    public long benchNanoTime() {
        return System.nanoTime();
    }
    
    @Benchmark
    public String benchGuavaJoiner() {
        return Joiner.on("").join("{it -> \"", "{user_id % 2}", System.nanoTime(), "\"}");
    }
    
    @Benchmark
    public String benchStringConcat() {
        return "{it -> \"" + "{user_id % 2}" + System.nanoTime() + "\"}";
    }
    
    @SuppressWarnings("StringBufferReplaceableByString")
    @Benchmark
    public String benchStringJoiner() {
        return new StringJoiner("").add("{it -> \"").add("{user_id % 2}").add(Long.toString(System.nanoTime())).add("\"}").toString();
    }
}
