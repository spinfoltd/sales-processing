package com.mprocessor.sales.thrds;

import com.mprocessor.sales.model.MBaseMessageType;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageQueueProvider{

	Queue<MBaseMessageType> messageQ =  new ConcurrentLinkedQueue<MBaseMessageType>();
	
	public MessageQueueProvider() {
		super();
	}

	public synchronized void sendMsg(MBaseMessageType msg){
		messageQ.add(msg);
	}

	public synchronized MBaseMessageType receiveMsg(){
		return messageQ.remove();
	}
	
	public synchronized Integer getSize(){
		return messageQ.size();
	}
	
	
}
