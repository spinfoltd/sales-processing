package com.mprocessor.sales.thrds;

import com.mprocessor.sales.cfg.SalesProcessingConfig;
import com.mprocessor.sales.model.MBaseMessageType;
import com.mprocessor.sales.model.MProducts;
import com.mprocessor.sales.model.MSale;
import com.mprocessor.sales.util.PrintUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class MessageReceiver implements Callable<Integer> {

	private MessageQueueProvider msgQue;
	private MProducts mProducts;
	private Integer maximumMsgsExpectedToReceive;

	public MessageReceiver(MessageQueueProvider msgQue, MProducts mProducts, int maximumMsgsExpectedToReceive) {
		this.msgQue = msgQue;
		this.mProducts = mProducts;
		this.maximumMsgsExpectedToReceive = maximumMsgsExpectedToReceive;
	}

	public Integer receiveAndProcessMessage(){
		MBaseMessageType recMsg = receiveMessage();
		return processMessage(recMsg);
	}

	public MBaseMessageType receiveMessage(){
		MBaseMessageType receivedMsg = msgQue.receiveMsg();
		return receivedMsg;
	}

	public Integer processMessage(MBaseMessageType msg){
		System.out.println(PrintUtil.getTabs(4)+"<<Processing>> Msg:{"+msg.toJSon()+"}");
		MSale.create(msg, mProducts);
		return mProducts.getTotalSalesRecorderd();

	}

	@Override
	public Integer call() throws Exception {
		while(!(msgQue.getSize() > 0) && mProducts.getTotalSalesRecorderd() < maximumMsgsExpectedToReceive  ){
			System.out.println(PrintUtil.getTabs(4)+"<Receiver Waiting for Msgs>" );
			TimeUnit.SECONDS.sleep(1);
		}
		MBaseMessageType receivedMsg  = msgQue.receiveMsg();
		int retVal =processMessage(receivedMsg);
		if( mProducts.getTotalSalesRecorderd() >0 && mProducts.getTotalSalesRecorderd()% SalesProcessingConfig.logProdAdjstOnMsgsReceived ==0){
			System.out.println("\n--------Receiver Pausing to accespt Messages---");
			MessageSenderReceiver.logProductAdjustments(getmProducts().getTotalSalesRecorderd(),
					getmProducts());
		}
		return retVal;
	}

	public MessageQueueProvider getMsgQue() {
		return msgQue;
	}

	public void setMsgQue(MessageQueueProvider msgQue) {
		this.msgQue = msgQue;
	}

	public MProducts getmProducts() {
		return mProducts;
	}

	public void setmProducts(MProducts mProducts) {
		this.mProducts = mProducts;
	}

}
