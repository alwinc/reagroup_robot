/**
 * @Copyright 2013
 * All rights reserved
 */
package com.winnegenic.reagroup.robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.winnegenic.reagroup.robot.command.Command;

/**
 * Robot.java
 * Started - Aug 7, 2013
 * 
 * Robot accepts commands similar to Visitor design pattern (ie. the robot gets visited by commands)
 * 
 */
public class Robot {
	private Board board;
	private Position position;
	private List<Command> commandSequence;
	
	private boolean isAlreadyStarted; // a flag to determine if the robot has already started receiving commands
	private int commandIndex;
	
	/**
	 * Main function for execution
	 */
	public static void main(String[] args){
		InputStreamReader isreader = null;
		BufferedReader reader = null;
		
		try {
			isreader = new InputStreamReader(System.in);
			reader = new BufferedReader(isreader);
		
			CommandParserDelegate parser = new CommandParserDelegate(reader, false);
			List<Command> commandSequence = parser.parseCommands();
			
			Robot standardRobot = new Robot();
			Board standardBoard = new Board(5, 5); // 5x5 board
			standardRobot.setBoard(standardBoard);
			standardRobot.setCommandSequence(commandSequence);
			standardRobot.executeCommands();
		} catch (IOException e) {
			
		} finally {
			IOUtils.closeQuietly(reader);
		}
	}
	
	public void executeCommands() {
		for(Command c: commandSequence) {
			this.accept(c);
		}
	}
	
	public void accept(Command c) {
		c.visit(this);
	}
	
	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}
	/**
	 * @param board the board to set
	 */
	public void setBoard(Board board) {
		this.board = board;
	}
	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}
	/**
	 * @return the commandSequence
	 */
	public List<Command> getCommandSequence() {
		return commandSequence;
	}
	/**
	 * @param commandSequence the commandSequence to set
	 */
	public void setCommandSequence(List<Command> commandSequence) {
		this.commandSequence = commandSequence;
	}
	/**
	 * @return the isAlreadyStarted
	 */
	public boolean isAlreadyStarted() {
		return isAlreadyStarted;
	}
	/**
	 * @param isAlreadyStarted the isAlreadyStarted to set
	 */
	public void setAlreadyStarted(boolean isAlreadyStarted) {
		this.isAlreadyStarted = isAlreadyStarted;
	}
	
	/**
	 * Prints information about the Robot
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder(position.toString());
		builder.append(" isAlreadyStarted: " + isAlreadyStarted);
		builder.append(" sequenceList: " + commandSequence);
		builder.append(" commandIndex: " + commandIndex);
		return builder.toString();
	}
}
