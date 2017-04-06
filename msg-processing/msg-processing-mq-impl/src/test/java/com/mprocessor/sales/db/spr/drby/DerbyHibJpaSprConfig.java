package com.mprocessor.sales.db.spr.drby;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Import(DerbyDataSourceHibPropConfig.class)
public class DerbyHibJpaSprConfig {

    @Autowired
    DataSource dataSource;
    @Autowired
    Properties hibernateProperties;
    // Following are needed to use Hibernate as JPA provided.
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter=
                new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean entMgrFactBean=
                new LocalContainerEntityManagerFactoryBean();
        entMgrFactBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        entMgrFactBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entMgrFactBean.setPackagesToScan("com.mprocessor.sales.db.mdl");
        entMgrFactBean.setDataSource(dataSource);
        entMgrFactBean.setJpaProperties(hibernateProperties);
        return entMgrFactBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(){
        JpaTransactionManager txnMgr = new JpaTransactionManager();
        txnMgr.setEntityManagerFactory(entityManagerFactory().getObject());
        return txnMgr;
    }
}
