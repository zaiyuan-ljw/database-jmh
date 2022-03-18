package com.sphereex.jmh.shardingsphere4;


import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;


import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class ShardingSpheres {

   public static DataSource createDataSource(final String path) {
       String configurationFile = System.getProperty("conf");
       try {
           if (configurationFile != null) {
               return YamlShardingDataSourceFactory.createDataSource(new File(configurationFile));
           } else {
               return YamlShardingDataSourceFactory.createDataSource(new File(ShardingSpheres.class.getResource(path).getFile()));
           }
       } catch (SQLException | IOException e) {
           throw new RuntimeException(e);
       }
   }

}
