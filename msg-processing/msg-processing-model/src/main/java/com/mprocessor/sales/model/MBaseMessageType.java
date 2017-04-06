package com.mprocessor.sales.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class MBaseMessageType implements Serializable{
	protected String productType;
	protected BigDecimal value;

	/*public MBaseMessageType() {
		super();
	}*/

	public MBaseMessageType(String productType, BigDecimal value) {
		super();
		this.productType = productType;
		this.value = value;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public MMsgType getMsgType(){
		return MMsgType.getMsgType(this.getClass());
	}

	public BigDecimal getValue(){
		return value;
	}


	public String toJSon() {
		return
				"msgType:'" + getMsgType()+ '\'' +
				",productType:'" + productType + '\'' +
				", value:" + value
				;
	}
	public String getJSon(int tab){
		return //PrintUtil.getTabsInNewLine(tab)+
				"Message{"+toJSon()+"}";
	}
}
