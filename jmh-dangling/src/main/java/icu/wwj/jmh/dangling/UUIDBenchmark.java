package icu.wwj.jmh.dangling;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.UUID;

@Fork(1)
@Measurement(iterations = 5, time = 3)
@Warmup(iterations = 5, time = 3)
@State(Scope.Benchmark)
public class UUIDBenchmark {
    
    @Benchmark
    public UUID randomUUID() {
        return UUID.randomUUID();
    }
}
