package com.mprocessor.sales.db.spr.embdded.hsql;


import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class SpringHibConf {
    @Bean
   public DataSource dataSource(){
       EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
       return databaseBuilder.setType(EmbeddedDatabaseType.HSQL)
               .addScripts("/db/create_db.sql","/db/insert_db.sql")
               .build();
   }
    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }

    //@PostConstruct
    public void startDataBaseManager(){
        //hsqldb
        DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", "" });

        //derby
        //DatabaseManagerSwing.main(new String[] { "--url", "jdbc:derby:memory:testdb", "--user", "", "--password", "" });

        //h2
        //DatabaseManagerSwing.main(new String[] { "--url", "jdbc:h2:mem:testdb", "--user", "sa", "--password", "" });
    }
}