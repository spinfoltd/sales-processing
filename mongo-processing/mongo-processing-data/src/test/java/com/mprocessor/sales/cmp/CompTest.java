package com.mprocessor.sales.cmp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Samantha on 01/04/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CompTest.class})
@Configuration
@ComponentScan(basePackages = {"com.mprocessor.sales.cmp"})
public class CompTest {
   @Autowired
    CompService compService;
    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void testComponent(){
        Assert.assertTrue(true);
        CompService compService = applicationContext.getBean(CompService.class);
        Assert.assertNotNull(compService);
    }
}
