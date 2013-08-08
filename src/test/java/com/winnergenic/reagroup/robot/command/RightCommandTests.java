/**
 * @Copyright 2013
 * All rights reserved
 */
package com.winnergenic.reagroup.robot.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.winnergenic.reagroup.robot.Board;
import com.winnergenic.reagroup.robot.Facing;
import com.winnergenic.reagroup.robot.Position;
import com.winnergenic.reagroup.robot.Robot;
import com.winnergenic.reagroup.robot.command.CommandLog;
import com.winnergenic.reagroup.robot.command.RightCommand;

/**
 * LeftCommandTests.java
 * Started - Aug 8, 2013
 */
public class RightCommandTests {
	
	private Robot robot;
	private Position rPosition;
	RightCommand rightCommand;
	
	/**
	 * Initialise a robot without any position at this point
	 */
	@Before
	public void setup() {
		robot = new Robot();
		robot.setBoard(new Board(5,5));
		robot.setAlreadyStarted(true);
		rightCommand = new RightCommand();
	}

	/**
	 * Starting from NORTH position ensure that the robot rotates to EAST
	 * on RIGHT command
	 */
	@Test
	public void fromNorthTest() {
		// set north position as the starting point of Robot
		rPosition = new Position(2,2,Facing.NORTH);
		robot.setPosition(rPosition);
		
		CommandLog result = rightCommand.visit(robot);
		
		assertEquals(Facing.EAST, robot.getPosition().getFacingPosition());
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
		
		CommandLog result = rightCommand.visit(robot);
		
		assertEquals(Facing.WEST, robot.getPosition().getFacingPosition());
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
		
		CommandLog result = rightCommand.visit(robot);
		
		assertEquals(Facing.SOUTH, robot.getPosition().getFacingPosition());
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
		
		CommandLog result = rightCommand.visit(robot);
		
		assertEquals(Facing.NORTH, robot.getPosition().getFacingPosition());
		assertEquals(CommandLog.OKCOMMANDLOG, result);
	}
	
	
	/**
	 * Ignore actions if the robot has not been initiated
	 */
	@Test
	public void ignoreCommandIfRobotIsNotInitiated() {
		robot.setAlreadyStarted(false);
		
		CommandLog result = rightCommand.visit(robot);
		
		assertNull(robot.getPosition()); // missing position still
		assertTrue(result.isCommandInQuestion());
		assertNotNull(result.getStatusReason());
	}
}
