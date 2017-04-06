package com.mprocessor.sales.cfg;


public class SalesProcessingConfig {
    // changes to change the count for logging the Products Adjustment
    public static int logProdAdjstOnMsgsReceived=50;//4;
    // changes to change the count for logging the Sales Recorded
    public static int logProdOnMsgsReceived_count= 10;//2;
    // change the number of messages that has to be sent for RandomMessageGenerator
    public static int totalMessagesToBeSent = 100;//16;

    public static String configJSonData(){
        return
                "totalMessagesToBeSent:"+ totalMessagesToBeSent+
        ",logProdAdjstOnMsgsReceived:"+logProdAdjstOnMsgsReceived +
        ",logProdOnMsgsReceived_count:"+logProdOnMsgsReceived_count

        ;
    }
    public static String configJSon(){
        return "\t\tConfiguration{"+configJSonData()+"}";
    }
}
