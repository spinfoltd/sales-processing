package com.mprocessor.sales.db.spr.mngo;


import com.mongodb.Mongo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.UnknownHostException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SprMongoClientFactTest.class})
@Configuration
public class SprMongoClientFactTest {

    @Document
    public static class Person{
        @Id
        private String id;
        private String name;
        private int age;
        @Version
        private Long version;
        public Person(){}
        public Person(String name, int age){
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getId() {
            return id;
        }
        @Override
        public String toString(){
            return "Person [id="+id+",name="+name+",age"+age+"]";
        }
    }
    //@Bean
    public MongoClientFactoryBean mongo(){
        MongoClientFactoryBean mfc = new MongoClientFactoryBean();
        mfc.setHost("localhost");
        return mfc;
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException {
        SimpleMongoDbFactory dbFactory =
                new SimpleMongoDbFactory(new Mongo(),"db1");
        return dbFactory;
    }

    @Bean
    public MongoTemplate mongoTemplate() throws UnknownHostException {
        return new MongoTemplate(mongoDbFactory());
    }

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void testMongoBean(){
            Assert.assertNotNull(mongoTemplate);
            mongoTemplate.insert(new Person("p2",3));
            // To list the persons after you insert the data is
            /*> use db1;
            switched to db db1
            > show collections;
            person
            > db.person.find();*/
    }

    @Test
    public void testGetPerson(){
        Assert.assertNotNull(mongoTemplate);
        //int persons = mongoTemplate.findAll(Person.class);
    }
}
