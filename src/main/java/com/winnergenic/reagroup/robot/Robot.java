/**
 * @Copyright 2013
 * All rights reserved
 */
package com.winnergenic.reagroup.robot;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.winnergenic.reagroup.robot.command.Command;
import com.winnergenic.reagroup.robot.command.CommandLog;

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
	
	private static final Logger log = Logger.getLogger(Robot.class);
	
	/**
	 * Main function for execution
	 * This robot has acceptable flags
	 * -f <filename>	uses a file (relative to execution point) as the input
	 * -s				triggers sensitive robot that will blow up on ANY errorneous input
	 * -b <int M>		changes the board size to be sized MxM
	 * @throws IOException can occur if we use a sensitive parser with errorneous input, or the input file was NOT found
	 */
	public static void main(String[] args) throws IOException{
		InputStreamReader isreader = null;
		BufferedReader reader = null;

		
		boolean usesFile = false;
		String filename = null;
		boolean isSensitiveParser = false;
		boolean changeBoardSize = false;
		int maxBoardSide = -1;
		// check if flags applied
		try {
			if (args.length > 0) {
				for(int i=0; i<args.length; i++) {
					if("-f".equalsIgnoreCase(args[i])) {
						if(args.length < i) {
							printErrorUsage();
							return;
						}
						usesFile = true;
						filename = args[i+1];
						i+=1; // increment already
						continue;
					} else if ("-s".equalsIgnoreCase(args[i])) {
						isSensitiveParser = true;
						continue;
					} else if ("-b".equalsIgnoreCase(args[i])) {
						if(args.length < i) {
							printErrorUsage();
							return;
						}
						changeBoardSize = true;
						maxBoardSide = Integer.valueOf(args[i+1]);
						i+=1; // increment already
					}
					else {
						printErrorUsage();
						return;
					}
				}
			}
		} catch (Exception e) {
			printErrorUsage();
			return;
		}
		
		Board board = new Board(5, 5); // 5x5 board
		if (changeBoardSize) {
			board = new Board(maxBoardSide,maxBoardSide);
		}
		
		try {
			if (usesFile) { // using file
				isreader = new InputStreamReader(new FileInputStream(filename));
			} else {
				isreader = new InputStreamReader(System.in);
			}
			reader = new BufferedReader(isreader);
		
			CommandParserDelegate parser = new CommandParserDelegate(reader, isSensitiveParser);
			List<Command> commandSequence = parser.parseCommands();
			
			Robot standardRobot = new Robot();
			standardRobot.setBoard(board);
			standardRobot.setCommandSequence(commandSequence);
			standardRobot.executeCommands();
		} finally {
			IOUtils.closeQuietly(reader);
		}
	}
	
	// prints the error usage line
	private static final void printErrorUsage() {
		StringBuilder sb = new StringBuilder("Incorrect usage:\n");
		sb.append("\t -f <filename> \t\t uses a file (relative to execution point) as the input\n");
		sb.append("\t -s \t\t\t triggers sensitive robot that will blow up on ANY errorneous input\n");
		sb.append("\t -b <int M> \t\t changes the board size to be sized MxM\n\n");
		sb.append("example usage: robot -f sampleinput1.txt -s -b 9");
		System.out.println(sb.toString());
	}
	
	/**
	 * This is the executable function for the robot once all
	 * the commands have been parsed and board is set.
	 * As this robot implements the Visitor Design pattern it will
	 * iterate through the command sequence list and use the Commands
	 * to visit itself
	 */
	public void executeCommands() {
		for(Command c: commandSequence) {
			this.accept(c);
		}
	}
	
	/**
	 * Visitor design pattern component of the Robot.
	 * The robot will accept a command and allow the command
	 * object to visit itself changing the state of the robot
	 * @param c command to be accepted
	 */
	public void accept(Command c) {
		CommandLog cl = c.visit(this);
		log.trace(cl);
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
