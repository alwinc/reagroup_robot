/**
 * @Copyright 2013
 * All rights reserved
 */
package com.winnergenic.reagroup.robot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.winnergenic.reagroup.robot.command.CommandLog;
import com.winnergenic.reagroup.robot.command.LeftCommand;

/**
 * LeftCommandTests.java
 * Started - Aug 8, 2013
 */
public class LeftCommandTests {
	
	private Robot robot;
	private Position rPosition;
	LeftCommand leftCommand;
	
	/**
	 * Initialise a robot without any position at this point
	 */
	@Before
	public void setup() {
		robot = new Robot();
		robot.setBoard(new Board(5,5));
		robot.setAlreadyStarted(true);
		leftCommand = new LeftCommand();
	}

	/**
	 * Starting from NORTH position ensure that the robot rotates to WEST
	 * on LEFT command
	 */
	@Test
	public void fromNorthTest() {
		// set north position as the starting point of Robot
		rPosition = new Position(2,2,Facing.NORTH);
		robot.setPosition(rPosition);
		
		CommandLog result = leftCommand.visit(robot);
		
		assertEquals(Facing.WEST, robot.getPosition().getFacingPosition());
		assertEquals(CommandLog.OKCOMMANDLOG, result);
	}
	
	/**
	 * Check rotation from SOUTH position
	 */
	@Test
	public void fromSouthTest() {
		// set north position as the starting point of Robot
		rPosition = new Position(2,2,Facing.SOUTH);
		robot.setPosition(rPosition);
		
		CommandLog result = leftCommand.visit(robot);
		
		assertEquals(Facing.EAST, robot.getPosition().getFacingPosition());
		assertEquals(CommandLog.OKCOMMANDLOG, result);
	}
	
	/**
	 * Check rotation from EAST position
	 */
	@Test
	public void fromEastTest() {
		// set north position as the starting point of Robot
		rPosition = new Position(2,2,Facing.EAST);
		robot.setPosition(rPosition);
		
		CommandLog result = leftCommand.visit(robot);
		
		assertEquals(Facing.NORTH, robot.getPosition().getFacingPosition());
		assertEquals(CommandLog.OKCOMMANDLOG, result);
	}
	
	/**
	 * Check rotation from WEST position
	 */
	@Test
	public void fromWestTest() {
		// set north position as the starting point of Robot
		rPosition = new Position(2,2,Facing.WEST);
		robot.setPosition(rPosition);
		
		CommandLog result = leftCommand.visit(robot);
		
		assertEquals(Facing.SOUTH, robot.getPosition().getFacingPosition());
		assertEquals(CommandLog.OKCOMMANDLOG, result);
	}
	
	
	/**
	 * Ignore actions if the robot has not been initiated
	 */
	@Test
	public void ignoreCommandIfRobotIsNotInitiated() {
		robot.setAlreadyStarted(false);
		
		CommandLog result = leftCommand.visit(robot);
		
		assertNull(robot.getPosition()); // missing position still
		assertTrue(result.isCommandInQuestion());
		assertNotNull(result.getStatusReason());
	}
}
