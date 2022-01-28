package icu.wwj.jmh.jdbc;

import java.sql.Connection;

public class UnpooledPointSelectWithCBenchmarkJDBC extends UnpooledPointSelectWithCBenchmarkBase {
    
    @Override
    public Connection getConnection() {
        return Jdbcs.getConnection();
    }
}
