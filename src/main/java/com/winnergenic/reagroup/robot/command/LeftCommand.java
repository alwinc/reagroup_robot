/**
 * @Copyright 2013
 * All rights reserved
 */
package com.winnergenic.reagroup.robot.command;

import com.winnergenic.reagroup.robot.Facing;
import com.winnergenic.reagroup.robot.Robot;

/**
 * LeftCommand.java
 * Started - Aug 7, 2013
 */
public class LeftCommand implements Command {

	@Override
	public CommandLog visit(Robot robot) {
		
		if(!robot.isAlreadyStarted()) { // ignore if robot has not yet initiated
			CommandLog ignoredCommand = new CommandLog(true, "Robot has not been PLACED. LEFT command ignored");
			return ignoredCommand;
		}
		
		Facing facePosition = robot.getPosition().getFacingPosition();
		
		Facing newFacePosition = null;
		if(Facing.NORTH.equals(facePosition)) {
			newFacePosition = Facing.WEST;
		} else if (Facing.SOUTH.equals(facePosition)) {
			newFacePosition = Facing.EAST;
		} else if (Facing.EAST.equals(facePosition)) {
			newFacePosition = Facing.NORTH;
		} else if (Facing.WEST.equals(facePosition)) {
			newFacePosition = Facing.SOUTH;
		}
		// assign the new face position
		robot.getPosition().setFacingPosition(newFacePosition);
		return CommandLog.OKCOMMANDLOG;
	}

}
