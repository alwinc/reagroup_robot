/**
 * @Copyright 2013
 * All rights reserved
 */
package com.winnegenic.reagroup.robot.command;

import com.winnegenic.reagroup.robot.Robot;

/**
 * ReportCommand.java
 * Started - Aug 7, 2013
 */
public class ReportCommand implements Command {
	
	@Override
	public CommandLog visit(Robot robot) {
		if(!robot.isAlreadyStarted()) { // ignore if robot has not yet initiated
			CommandLog ignoredCommand = new CommandLog(true, "Robot has not been PLACED. REPORT command ignored");
			return ignoredCommand;
		}
		
		System.out.println(robot.getPosition());
		return CommandLog.OKCOMMANDLOG;
	}

}
