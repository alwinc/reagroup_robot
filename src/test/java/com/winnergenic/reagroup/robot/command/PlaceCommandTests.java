/**
 * @Copyright 2013
 * All rights reserved
 */
package com.winnergenic.reagroup.robot.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.winnergenic.reagroup.robot.Board;
import com.winnergenic.reagroup.robot.Facing;
import com.winnergenic.reagroup.robot.Position;
import com.winnergenic.reagroup.robot.Robot;
import com.winnergenic.reagroup.robot.command.CommandLog;
import com.winnergenic.reagroup.robot.command.PlaceCommand;

/**
 * 
 * PlaceCommandTests.java
 * @created 08/08/2013
 *
 */
public class PlaceCommandTests {
	
	private Robot robot;
	private PlaceCommand placeCommand;
	
	/**
	 * Initialise a robot without any position at this point
	 */
	@Before
	public void setup() {
		robot = new Robot();
		robot.setBoard(new Board(5,5));
		robot.setAlreadyStarted(false);
	}

	/**
	 * Place test as initial placement
	 */
	@Test
	public void onePlaceTest() {
		int xPos = 0;
		int yPos = 0;
		Position position = new Position(xPos, yPos, Facing.NORTH);
		placeCommand = new PlaceCommand(position);
		CommandLog result = placeCommand.visit(robot);
		
		assertEquals(Facing.NORTH, robot.getPosition().getFacingPosition());
		assertEquals(xPos, robot.getPosition().getxCoord());
		assertEquals(yPos, robot.getPosition().getyCoord());
		
		// first placement so check started flag is true
		assertTrue(robot.isAlreadyStarted());
		assertEquals(CommandLog.OKCOMMANDLOG, result);
	}
	
	/**
	 * Place test AFTER initial placement
	 */
	@Test
	public void placeAfterStartedTest() {
		robot.setAlreadyStarted(true);
		int xPos = 2;
		int yPos = 3;
		Position position = new Position(xPos, yPos, Facing.NORTH);
		placeCommand = new PlaceCommand(position);
		CommandLog result = placeCommand.visit(robot);
		
		assertEquals(Facing.NORTH, robot.getPosition().getFacingPosition());
		assertEquals(xPos, robot.getPosition().getxCoord());
		assertEquals(yPos, robot.getPosition().getyCoord());
		
		// ensure flag is still set
		assertTrue(robot.isAlreadyStarted());
		
		assertTrue(result.isCommandInQuestion()); // questionable result
	}
	
	/**
	 * Place off table test as initial placement
	 */
	@Test
	public void placeOffTableInitialTest() {
		int xPos = 999;
		int yPos = 999;
		Position position = new Position(xPos, yPos, Facing.NORTH);
		placeCommand = new PlaceCommand(position);
		CommandLog result = placeCommand.visit(robot);
		
		// no position yet as it will be ignored!
		assertNull(robot.getPosition());
		
		// ensure flag is NOT set
		assertFalse(robot.isAlreadyStarted());
		
		assertTrue(result.isCommandInQuestion()); // questionable result
	}
	
	/**
	 * Place off table test after initial placement
	 */
	@Test
	public void placeOffTableAfterInitialTest() {
		robot.setAlreadyStarted(true);
		
		int xPos = 999;
		int yPos = 999;
		Position position = new Position(xPos, yPos, Facing.NORTH);
		placeCommand = new PlaceCommand(position);
		CommandLog result = placeCommand.visit(robot);
		
		// no position yet as it will be ignored!
		assertNull(robot.getPosition());
		
		// ensure flag is STILL set
		assertTrue(robot.isAlreadyStarted());
		
		assertTrue(result.isCommandInQuestion()); // questionable result
	}
}
