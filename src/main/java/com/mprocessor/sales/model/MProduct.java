package com.mprocessor.sales.model;

import com.mprocessor.sales.cmds.ISCommand;
import com.mprocessor.sales.util.PrintUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MProduct {
	private String name;
	List<MSale> sales= new ArrayList<>();

	public MProduct() {
	}

	public MProduct(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void addSale(MSale sale){
		this.sales.add(sale);
	}

	public void adjustSales(ISCommand command, BigDecimal adjVal, MMessageType3 msg){
		sales.stream().forEach(
				s -> command.execute(s,adjVal,msg)
		);
	}
	
	public BigDecimal totalSaleOnProduct(){
		BigDecimal total = sales.stream().map(s -> s.getTotalSaleValue()).reduce(BigDecimal.ZERO,BigDecimal::add);
		return total;
	}
	
	public String toJSonSalesList(int tab, boolean printAdj){
		return sales.stream().map(s -> "\n"+PrintUtil.getTabsInNewLine(tab)+"["+s.toJSonWithApplnHistory(tab,printAdj)+"]").collect(Collectors.joining(","));
	}
	
	public String toJSon(int tab, boolean printAdj) {
		return "Product{name:" + name + ",totalOfAllSales:"+totalSaleOnProduct()+", sales:{" + toJSonSalesList(tab+2,printAdj) + "}}";
	}
	
	
}
