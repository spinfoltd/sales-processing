package com.mprocessor.sales.thrds;


import com.mprocessor.sales.messgs.IMessageGenerator;
import com.mprocessor.sales.model.MBaseMessageType;
import com.mprocessor.sales.util.PrintUtil;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class MessageSender implements Runnable{
//public class MessageSender implements Callable<Integer>{
	private MessageQueueProvider msgQue;
	private IMessageGenerator msgGenerator;
	private int maxMsgsToBeSent;

	public MessageSender(MessageQueueProvider msgQue, int maxMsgsToBeSent) {
		this.msgQue = msgQue;
		this.maxMsgsToBeSent = maxMsgsToBeSent;
	}

	public void setMsgGenerator(IMessageGenerator msgGenerator) {
		this.msgGenerator = msgGenerator;
	}
	
	public MessageQueueProvider getMsgQue() {
		return msgQue;
	}

	public void setMsgQue(MessageQueueProvider msgQue) {
		this.msgQue = msgQue;
	}

	public IMessageGenerator getMsgGenerator() {
		return msgGenerator;
	}

	public int getMaxMsgsToBeSent() {
		return maxMsgsToBeSent;
	}

	public void setMaxMsgsToBeSent(int maxMsgsToBeSent) {
		this.maxMsgsToBeSent = maxMsgsToBeSent;
	}

	public void sendMessage(MBaseMessageType msg){
		msgQue.sendMsg(msg);
	}
	
	public void startSendingMsgs(){
		IntStream.rangeClosed(1,maxMsgsToBeSent).forEach(
				(i)-> {
					MBaseMessageType msg = msgGenerator.generateMessage();
					sendMessage(msg);
					System.out.println(PrintUtil.getTabs(4)+"<<SENT>>Sent Message:"+i+":TotalMsgs In Queue:"+msgQue.getSize()+"{"+msg.toJSon()+"}");
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
		);
		
	}
	
	public void run() {
		startSendingMsgs();
	}

	
	public Integer call() throws Exception {
		startSendingMsgs();
		return maxMsgsToBeSent;
	}
}
