package com.mprocessor.sales.model;

import java.math.BigDecimal;

public class AdjOperation {
	private String operation;
	private BigDecimal adjValue;

	public String getOperation() {
		return operation;
	}	public void setOperation(String operation) {
		this.operation = operation;
	}
	public BigDecimal getAdjValue() {
		return adjValue;
	}
	public void setAdjValue(BigDecimal adjValue) {
		this.adjValue = adjValue;
	}
}
