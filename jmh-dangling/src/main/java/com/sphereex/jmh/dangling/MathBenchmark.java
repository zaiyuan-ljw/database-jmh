package com.sphereex.jmh.dangling;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.ThreadLocalRandom;

@Fork(1)
@Warmup(iterations = 5, time = 3)
@Measurement(iterations = 5, time = 3)
@State(Scope.Benchmark)
public class MathBenchmark {
    
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    
    @Benchmark
    public long benchMod() {
        return random.nextLong(Long.MAX_VALUE) % 16;
    }
    
    @Benchmark
    public long benchAnd() {
        return random.nextLong(Long.MAX_VALUE) & 15;
    }
}
