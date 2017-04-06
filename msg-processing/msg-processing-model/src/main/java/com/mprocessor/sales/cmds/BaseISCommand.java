package com.mprocessor.sales.cmds;

public abstract class BaseISCommand implements ISCommand {
	protected SCommandTypeEnum commandType= null;
	
	public BaseISCommand(SCommandTypeEnum commandType) {
		super();
		this.commandType = commandType;
	}
	
	public  static BaseISCommand getCommand(String commandName){
		SCommandTypeEnum commandType = SCommandTypeEnum.getTypeFromName(commandName);
		return   CommandContainer.getInst().getCommands().stream()
					.filter( ct -> ct.commandType == commandType)
					.findAny()
					.orElse(null);
		
	}

	@Override
	public SCommandTypeEnum getCommandType() {
		return this.commandType;
	}
}
