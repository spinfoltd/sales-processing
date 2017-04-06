package com.mprocessor.sales.model;

import java.math.BigDecimal;

public class MMessageType2 extends MMessageType1 {
	protected Integer occurrence;
	
	public MMessageType2(String productType, BigDecimal value, Integer occurrence) {
		super(productType, value);
		this.occurrence = occurrence;
	}

	/*@Override
	public BigDecimal getValue() {
		if(null != occurrence) {
			return super.getValue().multiply(BigDecimal.valueOf(occurrence));
		}else{
			return super.getValue();
		}
	}*/

	@Override
	public String toJSon() {
		return
				super.toJSon()+
				",occurrence:" + occurrence
				 ;
	}
}
