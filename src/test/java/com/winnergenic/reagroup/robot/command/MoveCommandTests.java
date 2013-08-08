/**
 * @Copyright 2013
 * All rights reserved
 */
package com.winnergenic.reagroup.robot.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.winnergenic.reagroup.robot.Board;
import com.winnergenic.reagroup.robot.Facing;
import com.winnergenic.reagroup.robot.Position;
import com.winnergenic.reagroup.robot.Robot;

/**
 * MoveCommandTests.java
 * @created 09/08/2013
 *
 */
public class MoveCommandTests {

	private Board standardBoard;
	private MoveCommand moveCommand;
	private Robot robot;
	private Set<Position> edgeCaseScenarios;
	
	private static final Logger log = Logger.getLogger(MoveCommandTests.class);
	
	/**
	 * Initialise a robot already started
	 */
	@Before
	public void setup() {
		standardBoard = new Board(5, 5);
		// work out all edge case scenarios
		edgeCaseScenarios = calculateEdgeCases(standardBoard);
		robot = new Robot();
		robot.setBoard(standardBoard);
		robot.setAlreadyStarted(true);
		moveCommand = new MoveCommand();
	}

	
	/*
	 * This function will find out ALL of the edge cases and report with a list
	 * of scenarios where the robot should fail
	 */
	private Set<Position> calculateEdgeCases(Board board) {
		Set<Position> edgePositions = new HashSet<Position>();
		
		// work out bottom rows first
		for(int x=0; x<board.getMaxX(); x++) {
			Position anEdgePos = new Position(x, 0, Facing.SOUTH);
			edgePositions.add(anEdgePos);
		}
		
		// work out the top rows next
		for(int x=0; x<board.getMaxX(); x++) {
			Position anEdgePos = new Position(x, board.getMaxY()-1, Facing.NORTH);
			edgePositions.add(anEdgePos);
		}
		
		// work out the left column
		for(int y=0; y<board.getMaxY(); y++) {
			Position anEdgePos = new Position(0, y, Facing.WEST);
			edgePositions.add(anEdgePos);
		}
		
		// work out the right column
		for(int y=0; y<board.getMaxY(); y++) {
			Position anEdgePos = new Position(board.getMaxX()-1, y, Facing.EAST);
			edgePositions.add(anEdgePos);
		}
		
		return edgePositions;
	}


	/**
	 * Here we do a SINGLE test to calculate ENTIRE possible move combinations across this board
	 * to see if edges are ignored
	 * This is a VERY thorough (brute force) test done programmatically with EVERY
	 * test case considered while only the edge cases are checked to see if they are ignored
	 */
	@Test
	public void everyCombinationTest() {
		for(int x=0; x<robot.getBoard().getMaxX(); x++) {
			for(int y=0; y<robot.getBoard().getMaxY(); y++) {
				for(Facing face : Facing.values()) {
					Position testPosition = new Position(x,y,face);
					robot.setPosition(testPosition);
					CommandLog commandLog = moveCommand.visit(robot);
					log.trace("testing position " + testPosition);
					
					if(edgeCaseScenarios.contains(testPosition)) { // found an edge case!
						assertEquals(testPosition, robot.getPosition()); // robot chose NOT to move
						assertTrue(commandLog.isCommandInQuestion()); // command is questioned
						log.trace("FAIL");
					} else {
						log.trace("OK");
						assertEquals(CommandLog.OKCOMMANDLOG, commandLog);
						assertEquals(face, robot.getPosition().getFacingPosition()); // facing remained
						
						if (Facing.NORTH.equals(testPosition.getFacingPosition())) {
							assertEquals(testPosition.getyCoord()+1, robot.getPosition().getyCoord()); // ensure it went up
						} else if (Facing.SOUTH.equals(testPosition.getFacingPosition())) {
							assertEquals(testPosition.getyCoord()-1, robot.getPosition().getyCoord()); // ensure it went up
						} else if (Facing.EAST.equals(testPosition.getFacingPosition())) {
							assertEquals(testPosition.getxCoord()+1, robot.getPosition().getxCoord()); // ensure it went up
						} else if (Facing.WEST.equals(testPosition.getFacingPosition())) {
							assertEquals(testPosition.getxCoord()-1, robot.getPosition().getxCoord()); // ensure it went up
						}
					}
				}
			}
		}
	}
	
	
	/*
	 * This test ensures that robot chooses NOT to move if the robot has not yet been initialised
	 */
	@Test
	public void robotNotInitatedTestIgnored() {
		robot.setAlreadyStarted(false);
		Position testPosition = new Position(0, 0, Facing.NORTH);
		robot.setPosition(testPosition);
		
		CommandLog commandLog = moveCommand.visit(robot);
		assertEquals(testPosition, robot.getPosition()); // robot chose NOT to move
		assertTrue(commandLog.isCommandInQuestion()); // command is questioned
	}
}
