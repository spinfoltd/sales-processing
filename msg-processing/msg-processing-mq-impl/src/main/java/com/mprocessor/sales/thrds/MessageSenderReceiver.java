package com.mprocessor.sales.thrds;

import com.mprocessor.sales.cfg.SalesProcessingConfig;
import com.mprocessor.sales.messgs.RandomMessageGenerator;
import com.mprocessor.sales.model.MProducts;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class MessageSenderReceiver {


    public static MessageSender getSender(MessageQueueProvider queueProvider, int numberOfMsgsToSend ){
        MessageSender sender = new MessageSender(queueProvider, numberOfMsgsToSend);
        sender.setMsgGenerator(new RandomMessageGenerator());
        return sender;
    }

    public static MessageReceiver getReceiver(MessageQueueProvider queueProvider, MProducts products, int maxMessagesToBeExpected){
        MessageReceiver receiver = new MessageReceiver(queueProvider, products,maxMessagesToBeExpected);
        return receiver;
    }

    public static void startSending(ExecutorService execService, MessageSender sender ){
        execService.execute(sender);
    }

    public static void logProduct(Integer msgsProcessed, MProducts products){
        if(msgsProcessed % SalesProcessingConfig.logProdOnMsgsReceived_count == 0 ){
            System.out.println("\n\n"+"---------------------------------------");
            System.out.println("LOG Product Sales: ,MsgsProcessed:"+ msgsProcessed);
            System.out.println("---------------------------------------");
            System.out.println(products.toJSon(1,false));
            System.out.println("\n"+"-------------------------");
            System.out.println("END : LOG Product Sales:");
            System.out.println("---------------------------------------");
        }
    }

    public static void logProductAdjustments(Integer msgsProcessed, MProducts products){
        if(msgsProcessed % SalesProcessingConfig.logProdAdjstOnMsgsReceived == 0 ){
            System.out.println("\n\n-------------------------------------------");
            System.out.println("LOG Product Adjustments: ,MsgsProcessed:"+ msgsProcessed);
            System.out.println("-------------------------------------------");
            System.out.println(products.toJSon(1,true));
            System.out.println("\n-------------------------------------------");
            System.out.println("END :LOG Product Adjustments: ");
            System.out.println("---------------------------");
        }
    }

    public static void startReceiving(ExecutorService execService, int  maxMsgsToBeSent,MessageReceiver receiver ) throws InterruptedException, ExecutionException {
        int processedMsgsCount = 0;
        do{
            Future<Integer> processedMsgsCountResult = execService.submit(receiver);
            processedMsgsCount = processedMsgsCountResult.get();
            logProduct(processedMsgsCount,receiver.getmProducts());
            //logProductAdjustments(processedMsgsCount,receiver.getmProducts());
        }while(processedMsgsCount < maxMsgsToBeSent);
    }
}
