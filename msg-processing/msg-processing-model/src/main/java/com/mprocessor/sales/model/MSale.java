package com.mprocessor.sales.model;

import com.mprocessor.sales.cmds.BaseISCommand;
import com.mprocessor.sales.cmds.IAdjustCmd;
import com.mprocessor.sales.util.PrintUtil;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MSale {
	private MProduct belongToProduct;
	private BigDecimal indivSalValue;
	private BigDecimal totalSaleValue;
	private Date receivedTime;
	private MBaseMessageType saleIncurredFromMsg;
	private Integer occurrence;

	private Queue<MSaleHist> saleHistory = new ConcurrentLinkedQueue<>();
	
	public MSale(){
	}
	public MSale(MSale sale){
		this.belongToProduct = sale.belongToProduct;
		this.indivSalValue = BigDecimal.ZERO.add(sale.indivSalValue);
		this.totalSaleValue =BigDecimal.ZERO.add(sale.totalSaleValue);
		this.saleIncurredFromMsg = sale.saleIncurredFromMsg;
		this.occurrence = sale.occurrence.intValue();
	}

	public Queue<MSaleHist> getSaleHistory() {
		return saleHistory;
	}

	public void setSaleHistory(Queue<MSaleHist> saleHistory) {
		this.saleHistory = saleHistory;
	}

	public  void createHistory(MMessageType3 msg){
		MSaleHist newSale = new MSaleHist(this,msg);
		if(newSale.getSale().totalSaleValue.compareTo
				(newSale.getSale().indivSalValue.multiply(BigDecimal.valueOf(newSale.getSale().occurrence)))
				!=0
				)
		{
			System.out.println("EEEE");
		}
		saleHistory.add(newSale);
	}

	public void adjustVal(IAdjustCmd<BigDecimal, BigDecimal,BigDecimal> adjFunction, BigDecimal val){
		this.setIndivSalValue(adjFunction.adjust(this.getIndivSalValue(),val));
	}

	public void setIndivSalValue(BigDecimal indivSalValue) {
		this.indivSalValue = indivSalValue;
	}

	public BigDecimal getIndivSalValue() {
		return indivSalValue;
	}
	public Integer getOccurrence() {
		return occurrence;
	}
	public void addValue(BigDecimal addValue, MMessageType3 msg){
		createHistory(msg);
		this.indivSalValue = indivSalValue.add(addValue);
		calculateTotalValue();
	}
	
	public void subtractValue(BigDecimal addValue, MMessageType3 msg){
		createHistory(msg);
		this.indivSalValue =
				(indivSalValue.subtract(addValue).compareTo(BigDecimal.ZERO)<0)?
						BigDecimal.ZERO: indivSalValue.subtract(addValue);
		calculateTotalValue();
	}
	
	public void multiplyValue(BigDecimal addValue, MMessageType3 msg){
		createHistory(msg);
		this.indivSalValue = indivSalValue.multiply(addValue);
		calculateTotalValue();
	}

	public void addProduct(MProduct prod){
		this.belongToProduct = prod;
		prod.addSale(this);
	}

	public void processMsgGeneric(MBaseMessageType msg, MProducts prodTypeContainer){
		this.indivSalValue = msg.getValue();
		this.belongToProduct = prodTypeContainer.createIfNotExistsAndGet(msg.productType);
		this.receivedTime = new Date();
		this.addProduct(this.belongToProduct);
		this.saleIncurredFromMsg = msg;
	}

	public void processMsgType1(MMessageType1 msg, MProducts prodTypeContainer){
		processMsgGeneric(msg, prodTypeContainer);
		this.occurrence = 1;
		calculateTotalValue();
	}

	public void processMsgType2(MMessageType2 msg, MProducts prodTypeContainer){
		processMsgGeneric(msg, prodTypeContainer);
		this.occurrence = msg.occurrence;
		calculateTotalValue();
	}

	public void processMsgType3(MMessageType3 msg, MProducts prodTypeContainer){
		MProduct prodType = prodTypeContainer.getProduct(msg.productType);
		if(null != prodType){
			prodType.adjustSales(BaseISCommand.getCommand(msg.operation),msg.adjVal, msg);
		}
		processMsgGeneric(msg, prodTypeContainer);
		this.occurrence = msg.occurrence;
		calculateTotalValue();
	}

	public void processFromMessage(MBaseMessageType msg, MProducts container){
		switch(msg.getMsgType()){
			case MSG_TYPE_1:   processMsgType1((MMessageType1)msg, container);
				break;
			case MSG_TYPE_2:   processMsgType2((MMessageType2)msg, container);
				break;
			case MSG_TYPE_3:   processMsgType3((MMessageType3)msg, container);
				break;
		}
	}
	
	public  static MSale create(MBaseMessageType msg,MProducts container){
		MSale newSale = new MSale();
		newSale.processFromMessage(msg,container);
		return newSale;
	}
	

	public void calculateTotalValue(){
		this.totalSaleValue = this.indivSalValue.multiply(BigDecimal.valueOf(occurrence));
	}

	public BigDecimal getTotalSaleValue() {
		return totalSaleValue;
	}
	

	public String toJSon(int tab) {
		return "MSale{" +
				"totalSaleValue:" + totalSaleValue +
				//", receivedTime:" + receivedTime +
				",indivSalValue:" + indivSalValue +
				", occurrence:" + occurrence +
				//saleIncurredFromMsg.getJSon(tab+1)+
				'}';
	}
	
	public String toJSonWithApplnHistory(int tab, boolean printAdj) {
		return "MSale{" +
				"totalSaleValue:" + totalSaleValue +
				//", receivedTime:" + receivedTime +
				",indivSalValue:" + indivSalValue +
				", occurrence:" + occurrence +
				",totalAdjsts:" + getSaleHistory().size()+
				((!printAdj)?PrintUtil.getTabsInNewLine(tab+1)+",FirstMsg:"+saleIncurredFromMsg.getJSon(0):"")+
				((printAdj)?getAdjHistoryJSon(tab+1):"")+
				((printAdj)?(PrintUtil.getTabsInNewLine(tab+3)+"-FirstMsg-:"+ saleIncurredFromMsg.getJSon(tab+2)):"")+
				'}';
	}
	
	public String getAdjHistoryJSon(int tab){
		StringBuffer sb = new StringBuffer();
		List<MSaleHist> historyList = saleHistory.stream().collect(Collectors.toList());
		Collections.reverse(historyList);
		final AtomicInteger count = new AtomicInteger(0);
		historyList.stream().forEach(
				(h) -> {
					int adjNumber = historyList.size() - count.getAndAdd(1);
					sb.append(h.toJSon(tab,adjNumber));
				}
				);
		return sb.toString();
	}
	
}
