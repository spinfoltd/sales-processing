package com.mprocessor.sales.cmds;

import com.mprocessor.sales.model.MMessageType1;
import com.mprocessor.sales.model.MMessageType2;
import com.mprocessor.sales.model.MMessageType3;

import java.math.BigDecimal;

public class MMMessageTypeUtil {
	public static String prod1 = "prod1";
	public static String prod2 = "prod2";
	public static String prod3 = "prod3";
	
	public static MMessageType1 getMsgType1(String prod, int i){
		MMessageType1 mst1 = new MMessageType1(prod, BigDecimal.valueOf(i));
		return mst1;
	}
	
	public static MMessageType2 getMsgType2(String prod,int value,int occurrence){
		MMessageType2 mst2 = new MMessageType2(prod, BigDecimal.valueOf(value), occurrence);
		return mst2;
	}
	
	public static MMessageType3 getMsgType3(String prod,int saleVal,String operation,int adjVal){
		MMessageType3 mst2 = new MMessageType3(prod, BigDecimal.valueOf(saleVal), operation, BigDecimal.valueOf(adjVal));
		return mst2;
	}
	
	public static MMessageType1 getProd1MsgType1(int i){
		MMessageType1 mst1 = getMsgType1(MMMessageTypeUtil.prod1, i);
		return mst1;
	}
	public static MMessageType2 getProd1MsgType2(int value,int occurrence){
		MMessageType2 mst2 = getMsgType2(MMMessageTypeUtil.prod1, value, occurrence);
		return mst2;
	}
	
	public static MMessageType3 getProd1MsgType3ADD(int saleVal, int adjVal){
		MMessageType3 mst2 = getMsgType3(MMMessageTypeUtil.prod1, saleVal, SCommandTypeEnum.ADDCommandType.getCommandType(),adjVal);
		return mst2;
	}
	
	
	
	public static MMessageType1 getProd2MsgType1(int i){
		MMessageType1 mst1 = getMsgType1(MMMessageTypeUtil.prod2, i);
		return mst1;
	}
	
	public static MMessageType2 getProd2MsgType2(int value,int occurrence){
		MMessageType2 mst2 = getMsgType2(MMMessageTypeUtil.prod2, value, occurrence);
		return mst2;
	}
}
