package com.mprocessor.sales.thrds;

import com.mprocessor.sales.evnts.ExecServiceUtil;
import com.mprocessor.sales.messgs.IMessageGenerator;
import com.mprocessor.sales.messgs.RandomMessageGenerator;
import com.mprocessor.sales.model.MBaseMessageType;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;



public class MessageSenderTest {

	@Ignore
	public void testSendingMessages() {
		MessageSender sender = new MessageSender(new MessageQueueProvider(), 20);
		sender.setMsgGenerator(new RandomMessageGenerator());
		Assert.assertNotNull(sender.getMsgGenerator());
		Assert.assertNotNull(sender.getMsgQue());
		Assert.assertTrue(sender.getMaxMsgsToBeSent() >0 );
		ExecutorService execService = Executors.newFixedThreadPool(1);
        execService.execute(sender);
        execService.shutdown();
        ExecServiceUtil.waitUntilTastFinished(execService);
	}
	
	public static class Test2 implements Runnable{
		public IMessageGenerator msgGenerator= new RandomMessageGenerator();
		private MessageQueueProvider msgQue;
		public void sendMessage(MBaseMessageType msg){
			msgQue.sendMsg(msg);
			
		}
		
		public void run(){
			IntStream.range(1,10).sequential().forEachOrdered(
				(i)-> {
					MBaseMessageType msg = msgGenerator.generateMessage();
					System.out.println("Sender Thread: Sending Msg number:"+i);
					System.out.println("\tSending Message:"+msg.toJSon());
					sendMessage(msg);
				}
		);
	};
		 
	}
	
	
	@Test
	public void testSendingMessages2() {
		Test2 t2 = new Test2();
		t2.msgGenerator = new RandomMessageGenerator();
		t2.msgQue = new MessageQueueProvider();
		ExecutorService execService = Executors.newFixedThreadPool(1);
        execService.execute(t2);
        execService.shutdown();
        ExecServiceUtil.waitUntilTastFinished(execService);
	}

}
