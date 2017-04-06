package com.mprocessor.sales.db.spr.drby;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DerbyDataSourceHibPropConfig {

    private String derbyURL="jdbc:derby://localhost:1527/sample";
    private String derbySchema="sa";
    private String derbyUsername="sa";
    private String derbyPassword="sa";

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
        dataSource.setUrl(derbyURL);
        dataSource.setUsername(derbyUsername);
        dataSource.setPassword(derbyPassword);
        return dataSource;
    }
    @Bean
    public Properties hibernateProperties(){
        Properties p = new Properties();
        p.setProperty("hibernate.dialect","org.hibernate.dialect.DerbyDialect");
        p.setProperty("hibernate.show_sql","true");
        p.setProperty("hibernate.format_sql","true");
        p.setProperty("hibernate.hbm2ddl.auto","create");
        return p;
    }

}
