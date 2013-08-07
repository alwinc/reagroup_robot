/**
 * @Copyright 2013
 * All rights reserved
 */
package com.winnegenic.reagroup.robot.command;

import com.winnegenic.reagroup.robot.Robot;

/**
 * Command.java
 * Started - Aug 7, 2013
 * 
 * Command is the VISITOR interface for all other
 * commands that concretely implements the visitor
 * design pattern
 */
public interface Command {
	
	public static final String PLACE = "PLACE";
	public static final String MOVE = "MOVE";
	public static final String REPORT = "REPORT";
	public static final String LEFT = "LEFT";
	public static final String RIGHT = "RIGHT";
	
	/**
	 * 
	 * @param robot
	 * @return a command log will be returned if the move is questionable, otherwise it will be NULL or a status nonQuestionable
	 */
	public CommandLog visit(Robot robot); 
}
