package icu.wwj.jmh.jdbc;

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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Prepare Statement (ID = 1): 'COMMIT'
 * Prepare Statement (ID = 2): SELECT c FROM sbtest1 WHERE id=?
 * Prepare Statement (ID = 3): UPDATE sbtest3 SET k=k+1 WHERE id=?
 * Prepare Statement (ID = 4): UPDATE sbtest10 SET c=? WHERE id=?
 * Prepare Statement (ID = 5): DELETE FROM sbtest8 WHERE id=?
 * Prepare Statement (ID = 6): INSERT INTO sbtest8 (id, k, c, pad) VALUES (?, ?, ?, ?)
 * <p>
 * Statement: 'BEGIN'
 * Execute Statement: ID = 2
 * Execute Statement: ID = 2
 * Execute Statement: ID = 2
 * Execute Statement: ID = 2
 * Execute Statement: ID = 2
 * Execute Statement: ID = 2
 * Execute Statement: ID = 2
 * Execute Statement: ID = 2
 * Execute Statement: ID = 2
 * Execute Statement: ID = 2
 * Execute Statement: ID = 3
 * Execute Statement: ID = 4
 * Execute Statement: ID = 5
 * Execute Statement: ID = 6
 * Execute Statement: ID = 1
 */
@State(Scope.Group)
@Fork(3)
@Warmup(iterations = 10, time = 3)
@Measurement(iterations = 10, time = 3)
public class UnpooledReadWriteBenchmark {
    
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    
    private Connection connection;
    
    private PreparedStatement read;
    
    private PreparedStatement anUpdate;
    
    private PreparedStatement anotherUpdate;
    
    private PreparedStatement delete;
    
    private PreparedStatement insert;
    
    @Setup(Level.Iteration)
    public void setup() throws Exception {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sbtest_direct?useSSL=false&useServerPrepStmts=true&cachePrepStmts=true", "root", "");
        read = connection.prepareStatement("select c from sbtest1 where id = ?");
        anUpdate = connection.prepareStatement("update sbtest1 set k=k+1 where id = ?");
        anotherUpdate = connection.prepareStatement("update sbtest1 set c = ? where id = ?");
        delete = connection.prepareStatement("delete from sbtest1 where id = ?");
        insert = connection.prepareStatement("insert into sbtest1 (id, k, c, pad) values (?, ?, ?, ?)");
    }
    
    @Group
    @Benchmark
    public void benchReadWrite() throws Exception {
        connection.setAutoCommit(false);
        read.setInt(1, random.nextInt(100000));
        read.execute();
        anUpdate.setInt(1, random.nextInt(100000));
        anUpdate.execute();
        anotherUpdate.setString(1, randomString(120));
        anotherUpdate.setInt(2, random.nextInt(100000));
        anotherUpdate.execute();
        int id = random.nextInt(100000);
        delete.setInt(1, id);
        delete.execute();
        insert.setInt(1, id);
        insert.setInt(2, random.nextInt(Integer.MAX_VALUE));
        insert.setString(3, randomString(120));
        insert.setString(4, randomString(60));
        connection.commit();
    }
    
    private String randomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        while (sb.length() < length) {
            sb.append(ThreadLocalRandom.current().nextLong());
        }
        sb.setLength(length);
        return sb.toString();
    }
    
    @TearDown(Level.Iteration)
    public void tearDown() throws Exception {
        read.close();
        anUpdate.close();
        anotherUpdate.close();
        delete.close();
        insert.close();
        connection.close();
    }
}
