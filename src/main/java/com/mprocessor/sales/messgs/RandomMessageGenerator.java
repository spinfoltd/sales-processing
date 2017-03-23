package com.mprocessor.sales.messgs;

import com.mprocessor.sales.cmds.SCommandTypeEnum;
import com.mprocessor.sales.model.MBaseMessageType;
import com.mprocessor.sales.model.MMessageType1;
import com.mprocessor.sales.model.MMessageType2;
import com.mprocessor.sales.model.MMessageType3;

import java.math.BigDecimal;
import java.util.Random;

public class RandomMessageGenerator implements IMessageGenerator {

    public static enum SampleProductType {
        prod1,
        prod2,
        prod3,
        prod4,
        prod5;
    }

    public static int getRandomIntBetwn(int min, int max){
        return (new Random()).nextInt(max-min+1)+(min);
    }

    public static int getRandomOccurrence(){
        return getRandomIntBetwn(2,3);
    }

    public static int getRandomAdjVal(){
        return getRandomIntBetwn(1,2);
    }

    public static int getRandomSaleVal(){
        return getRandomIntBetwn(1,5);
    }

    public static String getRandomProduct(){
        int prodCardinal =  getRandomIntBetwn(1, SampleProductType.values().length);
        //prodCardinal = 1;
        return SampleProductType.values()[prodCardinal-1].name();

    }

    public static String getRandomOperation(){
        int prodCardinal =  getRandomIntBetwn(1, SCommandTypeEnum.values().length);
        return SCommandTypeEnum.values()[prodCardinal-1].getCommandType();
    }

    public static MMessageType1 createMsgType1(String prod, int saleVal){
        MMessageType1 mst1 = new MMessageType1(prod, BigDecimal.valueOf(saleVal));
        return mst1;
    }

    public static MMessageType2 createMsgType2(String prod, int saleVal, int occurrence){
        MMessageType2 mst2 = new MMessageType2(prod, BigDecimal.valueOf(saleVal), occurrence);
        return mst2;
    }

    public static MMessageType3 createMsgType3(String prod, int saleVal, String operation, int adjVal){
        MMessageType3 mst2 = new MMessageType3(prod, BigDecimal.valueOf(saleVal), operation, BigDecimal.valueOf(adjVal));
        return mst2;
    }

    public static MMessageType1 createRandomMsgType1(){
        return createMsgType1(getRandomProduct(),getRandomSaleVal());
    }

    public static MMessageType2 createRandomMsgType2(){
        return createMsgType2(getRandomProduct(),getRandomSaleVal(),getRandomOccurrence());
    }

    public static MMessageType3 createRandomMsgType3(){
        return createMsgType3(getRandomProduct(),getRandomSaleVal(),getRandomOperation(),getRandomAdjVal());
    }

    @Override
    public MBaseMessageType generateMessage() {
        int msgType = getRandomIntBetwn(1,3);
        //msgType=2;
        switch (msgType){
            case 1:  return createRandomMsgType1();
            case 2:  return createRandomMsgType2();
            case 3:  return createRandomMsgType3();
            default:  System.out.println("Invalid MessageType: "+msgType);
        }
        return null;
    }
}
