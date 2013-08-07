/**
 * @Copyright 2013
 * All rights reserved
 */
package com.winnegenic.reagroup.robot;

/**
 * Board.java
 * Started - Aug 7, 2013
 * 
 * I chose to deviate from the library board and simply construct my own board object
 * that has flexibility in board size.
 * 
 * This allows the board to be scalable as opposed to being restricted to a 5x5 board
 */
public class Board {
	
	private int maxX;
	private int maxY;
	
	/**
	 * Scalable Board constructor that allows a board to be constructed of 
	 * ANY size. This deviates from the scope of the specifications but allows
	 * scalability. Restrictions can be handled on the robot side
	 * @param maxX - max horizontal position
	 * @param maxY - max vertical position
	 */
	public Board(int maxX, int maxY) {
		this.maxX = maxX;
		this.maxY = maxY;
	}
	
	/**
	 * Simple test to check if the position is on the board
	 * @param position to be tested
	 * @return true if valid and on board, false otherwise
	 */
	public boolean isOnBoard(Position p) {
		// simple check that coordinates are within bounds, otherwise all others are invalid positions
		if ((p.getxCoord() <= maxX)  && // within max X
				(p.getxCoord() >= 0) && // assuming that smallest position is 0,0
				(p.getyCoord() <= maxY) &&
				(p.getyCoord() >= 0)) {
			return true;
		}
		return false;
	}
}
