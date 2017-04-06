package com.mprocessor.sales.cmds;

import java.util.ArrayList;
import java.util.List;

public class CommandContainer {

	private List<BaseISCommand> commands = new ArrayList<>();
	
	public static CommandContainer instance;
	private CommandContainer(){
		commands.add(new AddISCommand());
		commands.add(new SubISCommand());
		commands.add(new MultISCommand());
	}
	public static CommandContainer getInst(){
		if(null == instance){
			instance = new CommandContainer();
		}
		return instance;
	}
	
	public List<BaseISCommand> getCommands() {
		return commands;
	}
	
	private BaseISCommand getCommand(String cmdName){
		return commands.stream().filter(
				c -> c.commandType.getCommandType().equals(cmdName)
				).findAny().orElse(null); 
	}
	
	public BaseISCommand getAddCommand(){
		return getCommand(SCommandTypeEnum.ADDCommandType.getCommandType()); 
	}
	
	public BaseISCommand getSubtractCommand(){
		return getCommand(SCommandTypeEnum.SubtractCommandType.getCommandType()); 
	}
	
	public BaseISCommand getMultiplyCommand(){
		return getCommand(SCommandTypeEnum.MultiplyCommandType.getCommandType()); 
	}
	

}
