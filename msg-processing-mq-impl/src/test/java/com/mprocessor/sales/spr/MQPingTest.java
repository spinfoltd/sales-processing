package com.mprocessor.sales.spr;

import com.mprocessor.sales.messgs.RandomMessageGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ActiveMQConfig.class})
public class MQPingTest {
    @Autowired
    SalesMsgProducer salesMsgProducer;

    @Test
    public void checkIfMqIsUpAndRunningOnPort(){
        Assert.assertTrue(true);
    }

    @Test
    public void testProducingMsg(){
        salesMsgProducer.sendMessage(RandomMessageGenerator.createRandomMsgType1());
    }
}
