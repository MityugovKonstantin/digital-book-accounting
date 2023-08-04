package ru.mityugov.digitalbookaccounting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

@SpringBootApplication
@PropertySource("classpath:database/database.properties")
public class DigitalBookAccountingApplication {

    private final Environment environment;

    @Autowired
    public DigitalBookAccountingApplication(Environment environment) {
        this.environment = environment;
    }

    public static void main(String[] args) {
        SpringApplication.run(DigitalBookAccountingApplication.class, args);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty("dbdriver")));
        dataSource.setUrl(environment.getProperty("dburl"));
        dataSource.setUsername(environment.getProperty("dbusername"));
        dataSource.setPassword(environment.getProperty("dbpassword"));

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }


}
