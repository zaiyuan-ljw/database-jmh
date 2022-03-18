package com.sphereex.jmh.dangling;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Fork(1)
public class UUIDBenchmark {
    
    @Benchmark
    public UUID randomUUID() {
        return UUID.randomUUID();
    }
    
    @Benchmark
    public String randomUUIDToString() {
        return UUID.randomUUID().toString();
    }
    
    @Benchmark
    public UUID threadLocalRandomUUID() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return new UUID(random.nextLong(), random.nextLong());
    }
    
    @Benchmark
    public String threadLocalRandomUUIDToString() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return new UUID(random.nextLong(), random.nextLong()).toString();
    }
    
    @Benchmark
    public UUID threadLocalRandomEachTimeUUID() {
        return new UUID(ThreadLocalRandom.current().nextLong(), ThreadLocalRandom.current().nextLong());
    }
    
    @Benchmark
    public String threadLocalRandomEachTimeUUIDToString() {
        return new UUID(ThreadLocalRandom.current().nextLong(), ThreadLocalRandom.current().nextLong()).toString();
    }
}
