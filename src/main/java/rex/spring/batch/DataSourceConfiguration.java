package rex.spring.batch;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
* Pas de fichiers de properties pour garder le projet aussi simple que possible.
*/
@Configuration
public class DataSourceConfiguration {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/SpringBatchRex");
        dataSourceBuilder.username("postgres");
        return dataSourceBuilder.build();
    }

    
    @Bean
    public DataSource getPGDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName("SpringBatchRex");
        dataSource.setServerName("localhost");
        dataSource.setUser("postgres");
        //dataSource.setPassword("admin");
        return dataSource
   }
}
