package com.mprocessor.sales.thrds;


import com.mprocessor.sales.messgs.RandomMessageGenerator;
import com.mprocessor.sales.model.MBaseMessageType;
import org.junit.Assert;
import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;



public class MessageQueueProviderTest {

	@Test
	public void testSendMessage() throws InterruptedException {
		MessageQueueProvider queue = new MessageQueueProvider();
		RandomMessageGenerator rg = new RandomMessageGenerator();
		System.out.println("Initial Size:"+ queue.getSize());		
		queue.sendMsg( rg.createRandomMsgType1());
		Assert.assertTrue(queue.getSize() ==1 );
		queue.receiveMsg();
		Assert.assertTrue(queue.getSize() ==0 );
		
	}
	@Test
	public void sendQueue() throws InterruptedException{
		Queue<MBaseMessageType> messageQ =  new ConcurrentLinkedQueue<MBaseMessageType>();
		RandomMessageGenerator rg = new RandomMessageGenerator();
		System.out.println("Initial Size:"+ messageQ.size());		
		messageQ.add( rg.createRandomMsgType2());
		messageQ.add( rg.createRandomMsgType1());
		System.out.println("After Size:"+ messageQ.size());
		System.out.println(messageQ.remove().getJSon(1));
		System.out.println(messageQ.remove().getJSon(1));
	}
}
