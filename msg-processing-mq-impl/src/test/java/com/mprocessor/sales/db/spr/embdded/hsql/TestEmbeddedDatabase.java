package com.mprocessor.sales.db.spr.embdded.hsql;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ComponentScan(basePackages = {"com.mprocessor.sales.db.mdl"})
@ContextConfiguration(classes = {SpringHibConf.class})
public class TestEmbeddedDatabase {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void testInit(){
        Assert.assertNotNull(jdbcTemplate);
    }
}
