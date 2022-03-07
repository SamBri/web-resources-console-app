package com.codefilms.command;

public class ReportConsole implements Console {

	@Override
	public void showWelcomeMessage() {
		System.out.println("Welcome to DRY-GEN Console");
	}

	@Override
	public String getCommand(String command) {
		
		//1. let us have a list of acceptable commands
		//eg: result:generate students --all
				
	   //2. confirm that the command exist within the command block.
		
		return command;
	}

	
	

	
	
	

}
