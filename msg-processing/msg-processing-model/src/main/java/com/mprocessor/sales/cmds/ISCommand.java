package com.mprocessor.sales.cmds;

import com.mprocessor.sales.model.MMessageType3;
import com.mprocessor.sales.model.MSale;

import java.math.BigDecimal;

public interface ISCommand {
	
	public void execute(MSale sale,BigDecimal adjVal, MMessageType3 msg);
	public SCommandTypeEnum getCommandType();
}
