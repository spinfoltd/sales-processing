package com.mprocessor.sales.thrds;

import com.mprocessor.sales.cfg.SalesProcessingConfig;
import com.mprocessor.sales.cmds.CommandContainer;
import com.mprocessor.sales.cmds.MMMessageTypeUtil;
import com.mprocessor.sales.evnts.ExecServiceUtil;
import com.mprocessor.sales.model.MMessageType1;
import com.mprocessor.sales.model.MProducts;
import com.mprocessor.sales.model.MSale;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MessageSenderReceiveTest {
	MProducts mProducts;
	@Before
	public void init(){
		mProducts= new MProducts();
	}

	@Ignore
	public void testSendingReceivingMessages() throws InterruptedException, ExecutionException {
		int maxMessagesToBeSent = SalesProcessingConfig.totalMessagesToBeSent;
		MessageQueueProvider queueProvider  = new MessageQueueProvider();
		ExecutorService execService = Executors.newFixedThreadPool(2);		
		MessageReceiver receiver = MessageSenderReceiver.getReceiver(queueProvider, mProducts, maxMessagesToBeSent);
		MessageSender sender = MessageSenderReceiver.getSender(queueProvider, maxMessagesToBeSent);
		Assert.assertTrue(sender.getMsgQue() == receiver.getMsgQue());
		MessageSenderReceiver.startSending(execService, sender);
		MessageSenderReceiver.startReceiving(execService, maxMessagesToBeSent, receiver);
        execService.shutdown();
        ExecServiceUtil.waitUntilTastFinished(execService);
	}

	@Test
	public void testSaleAdjust() {
		{
			MMessageType1 msgT1_1 = MMMessageTypeUtil.getProd1MsgType1(3);
			MSale saleT1_1 = MSale.create(msgT1_1, mProducts);
			Assert.assertTrue(BigDecimal.valueOf(3).compareTo(saleT1_1.getTotalSaleValue())==0);
			mProducts.getProduct(MMMessageTypeUtil.prod1)
					.adjustSales(CommandContainer.getInst().getAddCommand(), BigDecimal.valueOf(2),MMMessageTypeUtil.getProd1MsgType3ADD(2, 4));
			Assert.assertTrue(BigDecimal.valueOf(5).compareTo(saleT1_1.getTotalSaleValue())==0);
			Assert.assertTrue(saleT1_1.getSaleHistory().size()==1);
			System.out.println(saleT1_1.getAdjHistoryJSon(1));
			System.out.println("--\n"+saleT1_1.toJSonWithApplnHistory(1,true));
		}
	}


	
}
