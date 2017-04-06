package com.mprocessor.sales.model;

import java.math.BigDecimal;

public class MMessageType1 extends MBaseMessageType {

	public MMessageType1(String productType, BigDecimal value) {
		super(productType, value);
	}

	@Override
	public String toJSon() {
		return super.toJSon();
	}
}
