package com.task1.tcp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 * A class that governs all execution of offered services, including parsing, execution of arithmetics, logging and representing output to the client
 * @author jkl070 and dro068
 *
 */

public class MyProtocol {

	private static final int modeWaiting = 0;
	private static final int modeAdd = 1;
	private static final int modeSub = 2;
	private static final int modeProcess = 3;
	private static final int modeGet = 4;
	private static final int modeLog = 5;
	private int state = modeWaiting;
	private static int v = 0;
	private String theOutput;
	private String question = ". To continue click Enter, To quit print Bye.";
	private LogObject log;
	private ArrayList<LogObject> list = new ArrayList<LogObject>();
	int x = -1;

    /**
     * returns the log information
     */
	public void getLogInfo() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		dateFormat.format(date);
		String iP = TCPServer.getIp();
		log = new LogObject(iP, date);
		list.add(log);
		x++;
	}
    /**
     * Processes the input from and too the server
     * @param theInput
     * @return
     */
	public String processInput(String theInput) {

		theOutput = null;
		if(theInput != null && theInput.equalsIgnoreCase("BYE")){
			System.exit(0);
		}
		if (state == modeWaiting) {
			theOutput = "Welcome. Current Value = " + v + ". Print 1 for addition, 2 for subtracktion, 3 for value, 4 for log, Bye to quit.";
			state = modeProcess;
		}
		else if (state == modeProcess) {
			if(theInput.equals("1")){
				theOutput = "Add, How much?";
				state = modeAdd;
			}
			else if (theInput.equals("2")){
				theOutput = "Sub, How much?";
				state = modeSub;
			}
			else if (theInput.equals("3")){
				theOutput = "to get v press Enter";
				state = modeGet;
			}
			else if (theInput.equals("4")){
				theOutput = "to get log press Enter";
				state = modeLog;

			}
		}

		else if (state == modeAdd){
			int i = Integer.parseInt(theInput);
			v = v + i;
			String s = ("Result = " + Integer.toString(v) + question);
			theOutput = s;
			state = modeWaiting;
			getLogInfo();
		}
		else if(state == modeSub){
			int i = Integer.parseInt(theInput);
			v = v - i;
			String s = ("Result = " + Integer.toString(v) + question);
			theOutput = s;
			state = modeWaiting;
			getLogInfo();
		}
		else if (state == modeGet) {
			String s = ("The value of v is = " + Integer.toString(v) + question);
			theOutput = s;
			state = modeWaiting;
		}
		else if (state == modeLog) {
			if(x >= 0){
				theOutput = "IPadress: " + list.get(x).getIp() + "Date: " + list.get(x).getDate() + ". Click Enter to get previous update.";
				state = modeLog;
				x--;
			}
			else {
				theOutput = "No more updates. Click Enter to proceed, or bye to quit.";
				state = modeWaiting;
			}
		}

		return theOutput;
	}
}