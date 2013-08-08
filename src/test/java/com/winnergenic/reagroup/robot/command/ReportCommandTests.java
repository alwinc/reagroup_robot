/**
 * @Copyright 2013
 * All rights reserved
 */
package com.winnergenic.reagroup.robot.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.winnergenic.reagroup.robot.Board;
import com.winnergenic.reagroup.robot.Facing;
import com.winnergenic.reagroup.robot.Position;
import com.winnergenic.reagroup.robot.Robot;
import com.winnergenic.reagroup.robot.command.CommandLog;
import com.winnergenic.reagroup.robot.command.PlaceCommand;
import com.winnergenic.reagroup.robot.command.ReportCommand;

/**
 * ReportCommandTests.java
 * @created 08/08/2013
 *
 */
public class ReportCommandTests {
	private Robot robot;
	private ReportCommand reportCommand;
	
	/**
	 * Initialise a robot without any position at this point
	 */
	@Before
	public void setup() {
		robot = new Robot();
		robot.setBoard(new Board(5,5));
		robot.setAlreadyStarted(false);
		reportCommand = new ReportCommand();
	}

	/**
	 * Report test after placement
	 */
	@Test
	public void simpleReportTest() {
		int xPos = 0;
		int yPos = 0;
		Position position = new Position(xPos, yPos, Facing.NORTH);
		PlaceCommand placeCommand = new PlaceCommand(position);
		CommandLog result = placeCommand.visit(robot);
		
		assertEquals(Facing.NORTH, robot.getPosition().getFacingPosition());
		assertEquals(xPos, robot.getPosition().getxCoord());
		assertEquals(yPos, robot.getPosition().getyCoord());

		// first placement so check started flag is true
		assertTrue(robot.isAlreadyStarted());
		
		// -- place complete
		
		result = reportCommand.visit(robot);
		// check it is still started
		assertTrue(robot.isAlreadyStarted());
		assertEquals(CommandLog.OKCOMMANDLOG, result);
	}
	
	/**
	 * Report test before placement
	 */
	@Test
	public void reportNoRobotTest() {
		assertFalse(robot.isAlreadyStarted());
		CommandLog result = reportCommand.visit(robot);
		// check it is still started
		assertFalse(robot.isAlreadyStarted());
		assertTrue(result.isCommandInQuestion()); // question the command!
	}
}
