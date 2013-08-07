/**
 * @Copyright 2013
 * All rights reserved
 */
package com.winnergenic.reagroup.robot.command;

import com.winnergenic.reagroup.robot.Facing;
import com.winnergenic.reagroup.robot.Position;
import com.winnergenic.reagroup.robot.Robot;

/**
 * MoveCommand.java
 * Started - Aug 7, 2013
 */
public class MoveCommand implements Command {

	@Override
	public CommandLog visit(Robot robot) {
		if(!robot.isAlreadyStarted()) { // ignore if robot has not yet initiated
			String message = "Robot has not been PLACED. MOVE command ignored";
			CommandLog ignoredCommand = new CommandLog(true, message);
			return ignoredCommand;
		}
		
		Position robotPos = robot.getPosition();
		Facing robotFace = robotPos.getFacingPosition();
		
		CommandLog commandLog = null;
		Position newPosition = null;
		if (Facing.NORTH.equals(robotFace)) {
			newPosition = new Position(robotPos.getxCoord(), robotPos.getyCoord()+1, robotFace); // y coord increment
		} else if (Facing.SOUTH.equals(robotFace)) {
			newPosition = new Position(robotPos.getxCoord(), robotPos.getyCoord()-1, robotFace); // y coord decrement
		} else if (Facing.EAST.equals(robotFace)) {
			newPosition = new Position(robotPos.getxCoord()+1, robotPos.getyCoord(), robotFace); // x coord increment
		} else if (Facing.WEST.equals(robotFace)) {
			newPosition = new Position(robotPos.getxCoord()-1, robotPos.getyCoord(), robotFace); // x coord decrement
		} 
		
		// check if new position should be on board
		if (robot.getBoard().isOnBoard(newPosition)) {
			robot.setPosition(newPosition);
			commandLog = CommandLog.OKCOMMANDLOG;
		} else { // else do NOTHING!!!
			String message = "Ignoring this call to move as I will NOT terminate myself. I'd rather self destruct in 5...4...3... just kidding";
			commandLog = new CommandLog(true, message);
		}
		
		return commandLog;
	}

}
