package icu.wwj.jmh.jdbc;

import java.sql.Connection;

public class UnpooledWriteOnlyBenchmarkJDBC extends UnpooledWriteOnlyBenchmarkBase {
    
    @Override
    public Connection getConnection() {
        return Jdbcs.getConnection();
    }
}
