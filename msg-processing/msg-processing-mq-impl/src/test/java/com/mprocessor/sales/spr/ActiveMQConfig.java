package com.mprocessor.sales.spr;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import javax.jms.ConnectionFactory;

@Configuration
@ComponentScan(basePackages = {"com.mprocessor.sales.spr"})
public class ActiveMQConfig {

    private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";

    private static final String ORDER_QUEUE = "order-queue";
    private static final String ORDER_RESPONSE_QUEUE = ORDER_QUEUE;
    //private static final String ORDER_RESPONSE_QUEUE = "order-response-queue";

    @Autowired
    SalesMsgConsumer salesMsgConsumer;


    @Bean
    ConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory amFact = new ActiveMQConnectionFactory();
        amFact.setBrokerURL(DEFAULT_BROKER_URL);
        return amFact;
    }

    @Bean
    public ConnectionFactory cachingConnectionFactory(){
        CachingConnectionFactory cachConFact = new CachingConnectionFactory();
        cachConFact.setTargetConnectionFactory(connectionFactory());
        cachConFact.setSessionCacheSize(10);
        return cachConFact;
    }

    // this method calls the onMessage method on the consumer
    @Bean
    public DefaultMessageListenerContainer getContainer(){
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(cachingConnectionFactory());
        container.setDestinationName(ORDER_QUEUE);
        container.setMessageListener(salesMsgConsumer);
        return container;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate tmplate = new JmsTemplate();
        tmplate.setConnectionFactory(connectionFactory());
        tmplate.setDefaultDestinationName(ORDER_RESPONSE_QUEUE);
        return tmplate;
    }

    @Bean
    MessageConverter converter(){
        return new SimpleMessageConverter();
    }

}
