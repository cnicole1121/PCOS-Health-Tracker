package config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class QuotesDatabaseConfig {

    @Bean(name = "quotesDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.quotes")
    public DataSource quotesDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "quotesJdbcTemplate")
    public JdbcTemplate quotesJdbcTemplate(@Qualifier("quotesDataSource") DataSource quotesDataSource) {
        return new JdbcTemplate(quotesDataSource);
    }
}
