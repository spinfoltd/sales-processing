package com.mprocessor.sales.cmds;

import com.mprocessor.sales.model.MMessageType3;
import com.mprocessor.sales.model.MSale;

import java.math.BigDecimal;

public class AddISCommand extends BaseISCommand {

	public AddISCommand() {
		super(SCommandTypeEnum.ADDCommandType);
	}

	@Override
	public void execute(MSale sale,BigDecimal adjVal,MMessageType3 msg){
		sale.addValue(adjVal,msg);
	}
	

}
