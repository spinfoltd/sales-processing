package com.mprocessor.sales.exc;


import com.mprocessor.sales.cfg.SalesProcessingConfig;
import com.mprocessor.sales.evnts.ExecServiceUtil;
import com.mprocessor.sales.model.MProducts;
import com.mprocessor.sales.thrds.MessageQueueProvider;
import com.mprocessor.sales.thrds.MessageReceiver;
import com.mprocessor.sales.thrds.MessageSender;
import com.mprocessor.sales.thrds.MessageSenderReceiver;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SalesProcessingExec {

    public void startSalesProcessing() throws ExecutionException, InterruptedException {
        MProducts mProducts = new MProducts();
        MessageQueueProvider queueProvider  = new MessageQueueProvider();
        ExecutorService execService = Executors.newFixedThreadPool(2);
        MessageReceiver receiver = MessageSenderReceiver.getReceiver(queueProvider, mProducts, SalesProcessingConfig.totalMessagesToBeSent);
        MessageSender sender = MessageSenderReceiver.getSender(queueProvider, SalesProcessingConfig.totalMessagesToBeSent);
        MessageSenderReceiver.startSending(execService, sender);
        MessageSenderReceiver.startReceiving(execService, SalesProcessingConfig.totalMessagesToBeSent, receiver);
        execService.shutdown();
        ExecServiceUtil.waitUntilTastFinished(execService);
    }


    public static void main(String args[]){
        System.out.println("--------------------------");
        System.out.println("<<START>> SALES PROCESSING");
        System.out.println(SalesProcessingConfig.configJSon());
        System.out.println("--------------------------");

        SalesProcessingExec processing = new SalesProcessingExec();
        try {
            processing.startSalesProcessing();
        } catch (ExecutionException e) {
            System.out.println("An Unexpected Error Happen: Please rerun the Program");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("An Unexpected Error Happen: Please rerun the Program");
        }
        System.out.println("------------------------");
        System.out.println(SalesProcessingConfig.configJSon());
        System.out.println("<<END>> SALES PROCESSING");
        System.out.println("------------------------");

    }

}
