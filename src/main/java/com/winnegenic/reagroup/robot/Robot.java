/**
 * @Copyright 2013
 * Work produced by Winnergenic Pty Ltd
 * All rights reserved
 */
package com.winnegenic.reagroup.robot;

import java.util.List;

import com.winnegenic.reagroup.robot.command.Command;

/**
 * Robot.java
 * Started - Aug 7, 2013
 */
public class Robot {
	private Board board;
	private Position position;
	private List<Command> commandSequence;
	
	private boolean isAlreadyStarted; // a flag to determine if the robot has already started receiving commands
	
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
	
}
