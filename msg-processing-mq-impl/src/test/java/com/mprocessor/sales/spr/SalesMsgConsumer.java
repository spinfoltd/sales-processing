package com.mprocessor.sales.spr;

import com.mprocessor.sales.model.MBaseMessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@Component
public class SalesMsgConsumer implements MessageListener{

    static final Logger LOG = LoggerFactory.getLogger(SalesMsgConsumer.class);

    @Autowired
    MessageConverter messageConverter;

    @Override
    public void onMessage(Message message) {
        try {
            LOG.info("Received Msg:"+message.getJMSMessageID());
            MBaseMessageType msg = (MBaseMessageType) messageConverter.fromMessage(message);
            System.out.println("RRrecievedMsg:\n"+msg.toJSon());
            LOG.info(msg.toJSon());
        } catch (JMSException e) {
            LOG.info("Message Could not be received");
            e.printStackTrace();
        }
    }
}
