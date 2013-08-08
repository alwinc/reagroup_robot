/**
 * @Copyright 2013
 * All rights reserved
 */
package com.winnergenic.reagroup.robot;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * BoardTests.java
 * @created 09/08/2013
 *
 */
public class BoardTests {

	/*
	 * Tests a 5x5 board basic
	 */
	@Test
	public void standardBoardTest() {
		Board stdBoard = new Board(5,5);
		
		// middle board
		Position testPosition = new Position(2, 2, Facing.NORTH);
		assertTrue(stdBoard.isOnBoard(testPosition));
		
		// edge position now
		testPosition = new Position(4,4,Facing.NORTH);
		assertTrue(stdBoard.isOnBoard(testPosition));
		
		testPosition = new Position(0,0,Facing.SOUTH);
		assertTrue(stdBoard.isOnBoard(testPosition));
		
		// testing off board
		testPosition = new Position(7,7,Facing.WEST);
		assertFalse(stdBoard.isOnBoard(testPosition));
		
		testPosition = new Position(-1,-1,Facing.WEST);
		assertFalse(stdBoard.isOnBoard(testPosition));
	}
	
	/*
	 * Larger board tests
	 */
	public void largerBoardTest() {
		Board largeBoard = new Board(555,555);
		
		// middle board
		Position testPosition = new Position(255, 255, Facing.NORTH);
		assertTrue(largeBoard.isOnBoard(testPosition));
		
		// edge position now
		testPosition = new Position(554,554,Facing.NORTH);
		assertTrue(largeBoard.isOnBoard(testPosition));
		
		testPosition = new Position(0,0,Facing.SOUTH);
		assertTrue(largeBoard.isOnBoard(testPosition));
		
		// testing off board
		testPosition = new Position(800,0,Facing.WEST);
		assertFalse(largeBoard.isOnBoard(testPosition));
		
		testPosition = new Position(-1,-1,Facing.WEST);
		assertFalse(largeBoard.isOnBoard(testPosition));
	}
}
