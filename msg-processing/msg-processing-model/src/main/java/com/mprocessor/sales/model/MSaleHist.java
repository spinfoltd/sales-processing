package com.mprocessor.sales.model;

import com.mprocessor.sales.util.PrintUtil;

public class MSaleHist {
	MSale sale;
	MMessageType3 adjstmntCozOfMsg;
	
	public MSaleHist(MSale sale, MMessageType3 type3Msg){
		this.sale = new MSale(sale);
		this.adjstmntCozOfMsg = type3Msg;
	}

	public MSale getSale() {
		return sale;
	}

	public String toJSon(int tab, int adjNumber) {
		return PrintUtil.getTabsInNewLine(tab+1)+"<<ADJUSTMENT  : "+adjNumber+">>:"
				+PrintUtil.getTabsInNewLine(tab+3) +"<ADJ_DUE2_MSG>:" + adjstmntCozOfMsg.toJSon()
				+PrintUtil.getTabsInNewLine(tab+2) +"<PREV-"+sale.toJSon(tab+3)+">"

				;
	}
}
