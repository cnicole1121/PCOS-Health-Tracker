package config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class MindfulnessDatabaseConfig {

    @Bean(name = "mindfulnessDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mindfulness")
    public DataSource mindfulnessDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mindfulnessJdbcTemplate")
    public JdbcTemplate mindfulnessJdbcTemplate(@Qualifier("mindfulnessDataSource") DataSource mindfulnessDataSource) {
        return new JdbcTemplate(mindfulnessDataSource);
    }
}
