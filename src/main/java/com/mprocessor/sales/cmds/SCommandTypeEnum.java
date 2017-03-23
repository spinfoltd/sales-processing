package com.mprocessor.sales.cmds;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public  enum SCommandTypeEnum {
	ADDCommandType("add"),
	SubtractCommandType("subtract"),
	MultiplyCommandType("multiply")
	;
	
	private SCommandTypeEnum(String commandType) {
		this.commandType = commandType;
	}

	private String commandType;

	public String getCommandType() {
		return commandType;
	}
	
	public static SCommandTypeEnum getTypeFromName(String name){
		List<SCommandTypeEnum>
			commandTypes =
				Arrays.stream(SCommandTypeEnum.values())
			.filter(sCommandType -> sCommandType.getCommandType().equalsIgnoreCase(name))
			.collect(Collectors.toList());
		
		if(null != commandTypes && commandTypes.size() ==1){
			return commandTypes.get(0);
		}
		//@Todo you should throw an exception 
		return null;
	}
}
