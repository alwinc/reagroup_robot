/**
 * @Copyright 2013
 * All rights reserved
 */
package com.winnegenic.reagroup.robot.command;

import com.winnegenic.reagroup.robot.Board;
import com.winnegenic.reagroup.robot.Position;
import com.winnegenic.reagroup.robot.Robot;

/**
 * PlaceCommand.java
 * Started - Aug 7, 2013
 */
public class PlaceCommand implements Command {
	private Position placePosition;
	
	/**
	 * Constructor for overall Place Command with a 
	 * constructed Position object
	 * @param placePosition starting position of the PLACE command
	 */
	public PlaceCommand(Position placePosition) {
		this.placePosition = placePosition;
	}
	
	@Override
	public CommandLog visit(Robot robot) {
		CommandLog retVal = null;
		// Check if the robot has already started and a Place command is coming in late
		if (robot.isAlreadyStarted()) {
			retVal = new CommandLog(true, "A PLACE has been requested after robot has already been moving. This is an ACT of God!");
		}
		robot.setAlreadyStarted(true); // get this going
		
		Board robotBoard = robot.getBoard();
		if (robotBoard.isOnBoard(placePosition)) {
			robot.setPosition(placePosition); // sets the position if it is on the board
		} else {
			retVal = new CommandLog(true, "Robot got PLACED off the table! How dare you!");
		}
		return retVal; // nullable return value
	}

	/**
	 * @return the placePosition
	 */
	public Position getPlacePosition() {
		return placePosition;
	}

	/**
	 * @param placePosition the placePosition to set
	 */
	public void setPlacePosition(Position placePosition) {
		this.placePosition = placePosition;
	}

}
