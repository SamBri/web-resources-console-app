package com.codefilms.runnable;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

import com.codefilms.command.Command;
import com.codefilms.command.Console;
import com.codefilms.command.ReportCommand;
import com.codefilms.command.ReportConsole;

public class App {

	
	
	
	public static void main(String[] args) throws URISyntaxException {
	
	// on start, display welcome message to end user.
	 Console theTeacherConsole = new ReportConsole();
	 //theTeacherConsole.showWelcomeMessage();
	 
	//1. end user enters command pattern: report:generate student --all
	 Scanner input = new Scanner(System.in);
	 String theEnteredCommand = null;
	 while(true) {
	 System.out.println("DRY-GEN ~");
	 System.out.print("$ ");
	 String theTeacherCommand = input.nextLine();//read end user command
     theEnteredCommand = theTeacherConsole.getCommand(theTeacherCommand);
     Command theReportCommand = null;
     theReportCommand = new ReportCommand();
     boolean theCmdExists = true;
     theCmdExists = theReportCommand.assertCommand(theEnteredCommand);
     if(!theCmdExists)
    	System.out.println("no such command");
     else 
     {   /* start process to send sms to all parents.
    	  * how is console alpp going to communicate with sms-distribution-service? app
    	  * call a method communicate to remote resource 
    	  * 
    	  * 
    	  * */
    	 
    	
    	 // split the entered command to paths to the remote resource
    	 String[] remoteResourcePaths = theEnteredCommand.split(":");
    	String tempVar = null;
    	// String[] t = null;
    	ArrayList<String> pars = new ArrayList<String>();
    	for(int i = 0; i < remoteResourcePaths.length; i++)
    		pars.add(i, remoteResourcePaths[i]);
        
    	
    	 String[] r = pars.get(0).split(" ");
    	 String[] s = pars.get(1).split(" ");
    	 String theExecutable =r[1];
    	 String theFileName = s[0];
    	 String theQuery = s[1];
    	 
    	 URL theUrl; 
    	 String theRemoteURL = 
    	"http://localhost:8080/webresources/console/sms" + "/" + theExecutable +"/" + theFileName + "?query=" + theQuery;
    	 URI d = URI.create(theRemoteURL);
    	 
		try {
			 theUrl = new URL(d.toString()); //communicate to the remote resource using an api
			 System.out.println("theURL:" + theUrl.toString());
	    	 HttpURLConnection theRemoteResource = (HttpURLConnection) theUrl.openConnection();
	    	 theRemoteResource.setRequestMethod("GET");
			 theRemoteResource.connect();
			 InputStream apiResponseStream = theRemoteResource.getInputStream();
			 BufferedReader br = null;
			 br = new BufferedReader(new InputStreamReader(apiResponseStream));
			 String apiResponseBody = null;
			 apiResponseBody = br.readLine();
			 int apiResponseCode = theRemoteResource.getResponseCode();
			 System.out.println("api response message:"+apiResponseBody);
			 System.out.println("api response code:"+apiResponseCode);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
     	
    	 System.out.println("Command executed");
     }
     
     
	 }
		

	}
	
	
	

}
