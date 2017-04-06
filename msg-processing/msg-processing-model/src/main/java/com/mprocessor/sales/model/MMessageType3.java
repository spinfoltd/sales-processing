package com.mprocessor.sales.model;

import java.math.BigDecimal;

public class MMessageType3 extends MMessageType2 {
	protected String operation;
	protected BigDecimal adjVal;
	
	public MMessageType3(String productType, BigDecimal saleVal, Integer occurrence, String operation,BigDecimal adjVal) {
		super(productType, saleVal,occurrence);
		this.operation = operation;
		this.adjVal = adjVal;
	}
	
	public MMessageType3(String productType, BigDecimal saleVal, String operation,BigDecimal adjVal) {
		this(productType, saleVal,1,operation,adjVal);
	}

	public BigDecimal getAdjVal() {
		return adjVal;
	}

	@Override
	public String toJSon() {
		return //super.toJSon()+
				"msgType:'" + getMsgType()+ '\'' +
				",productType:'" + productType + '\'' +
				",operation:'" + operation + '\'' +
				", adjVal:" + adjVal +
				", value:" + value
				+",occurrence:" + occurrence
				;

	}
}
