package com.mprocessor.sales.db.spr.drby;


import com.mprocessor.sales.db.mdl.Job;
import com.mprocessor.sales.db.spr.drby.rep.IJobRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(classes = {DerbyHibJpaSprConfig.class,DerbyRepoConfig.class})
public class DerbyJpaRepoHibSpringTest {
    @Autowired
    IJobRepository jobRepository;

    @Test
    public void testInit(){
        Assert.assertNotNull(jobRepository);
    }
    @Test
    public void insertSampleJob(){
        jobRepository.save(new Job("Job3","JobDesc3"));

    }
}
