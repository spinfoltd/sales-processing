package com.mprocessor.sales.cmds;

import com.mprocessor.sales.model.MMessageType3;
import com.mprocessor.sales.model.MSale;

import java.math.BigDecimal;

public class SubISCommand extends BaseISCommand {

	public SubISCommand() {
		super(SCommandTypeEnum.SubtractCommandType);
	}

	@Override
	public void execute(MSale sale,BigDecimal adjVal,MMessageType3 msg){
		sale.subtractValue(adjVal, msg);
	}
	

}
