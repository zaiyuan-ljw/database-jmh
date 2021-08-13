package icu.wwj.jmh.shardingsphere3;

import io.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class ShardingSpheres {
    
    public static DataSource createDataSource(String path) {
        File file = new File(ShardingSpheres.class.getResource(path).getFile());
        try {
            return YamlShardingDataSourceFactory.createDataSource(file);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
