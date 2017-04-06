package com.mprocessor.sales.cmds;

import com.mprocessor.sales.model.MMessageType3;
import com.mprocessor.sales.model.MSale;

import java.math.BigDecimal;

public class MultISCommand extends BaseISCommand {

	public MultISCommand() {
		super(SCommandTypeEnum.MultiplyCommandType);
	}

	@Override
	public void execute(MSale sale,BigDecimal adjVal,MMessageType3 msg){
		sale.multiplyValue(adjVal,msg);
	}

}
