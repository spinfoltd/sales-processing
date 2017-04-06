package com.mprocessor.sales.spr;


import com.mprocessor.sales.model.MBaseMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class SalesMsgProducer {
    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(final MBaseMessageType msg){
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(msg);
            }
        });
    }
}
