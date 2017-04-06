package com.mprocessor.sales.db.spr.mngo;


import com.mongodb.Mongo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.UnknownHostException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SimpleSprMongoClientTest.class})
@Configuration
public class SimpleSprMongoClientTest {
    @Bean
    public  Mongo  mongo() throws UnknownHostException {
        return new Mongo("localhost");
    }

    @Autowired
    Mongo mongo;

    @Test
    public void testMongoAndListAllDatabasesAndCollections()
    {
        Assert.assertNotNull(mongo);
        System.out.println(mongo.getAddress().getPort());
        mongo.getDatabaseNames().forEach(
                db -> System.out.println(db)
        );
        // The following only displays the --admin database;
        mongo.getUsedDatabases().forEach(
                db -> {
                    System.out.println("DBName:"+db.getName());
                    db.getCollectionNames().forEach(
                        c -> System.out.println("\t Collection:"+c)
                    );

                }
        );
        // This lists all the databases;
        mongo.getDatabaseNames().forEach(
                dbName-> {
                    System.out.println("DDD222:DBName:"+dbName);
                    mongo.getDB(dbName).getCollectionNames().forEach(
                            c -> System.out.println("\t Collection:"+c)
                    );
                }
        );
    }

}
