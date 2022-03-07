package com.codefilms.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

// this object contains the report commands.
public class ReportCommand implements Command {
	
	//the object that houses all the command.
	private final static String commandBlock = "report_command_block.txt";
	//public static File commandBlockFile = new File("report_command_block.txt");

	@Override
	public boolean assertCommand(String command) {
		
		
		InputStream ioStream = this.getClass().getClassLoader().getResourceAsStream("report_command_block.txt");
		
		if(ioStream == null)
			throw new IllegalArgumentException("not found");
		
		InputStreamReader isr = new InputStreamReader(ioStream);
		BufferedReader in = new BufferedReader(isr);
	
		//2. confirm that the entered command exists in the commandblock */
		String theReadCmd = null;
			
		try {
			theReadCmd = in.readLine();
			if(!command.equals(theReadCmd))
				return false;
			else 
				return true; // the read command exist
		} catch (IOException e) {
			System.out.println("assertCommand error:"+e.getMessage());
		}
		
		return false; // by default command entered from end user may be erroreous;
}

	@Override
	public String createCommand(String command) {
		// TODO Auto-generated method stub
		return null;
	}

}
