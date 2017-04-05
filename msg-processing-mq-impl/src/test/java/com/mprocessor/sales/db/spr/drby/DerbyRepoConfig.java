package com.mprocessor.sales.db.spr.drby;

import com.mprocessor.sales.db.spr.drby.rep.IJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Samantha on 04/04/2017.
 */
@Configuration
@ComponentScan(basePackages = {"com.mprocessor.sales.db.spr.drby.rep"})
@EnableJpaRepositories(basePackages = {"com.mprocessor.sales.db.spr.drby.rep"})
public class DerbyRepoConfig {
    @Autowired
    IJobRepository iJobRepository;
}
