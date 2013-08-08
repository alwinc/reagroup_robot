/**
 * @Copyright 2013
 * All rights reserved
 */
package com.winnergenic.reagroup.robot;

/**
 * Position.java
 * Started - Aug 7, 2013
 */
public class Position {
	private int xCoord;
	private int yCoord;
	private Facing facingPosition;

	/**
	 * Position constructor
	 * This constructor assumes all validations has been handled by the controller class
	 * 
	 * @param x - x position
	 * @param y - y position
	 * @param facingPosition - north, south, east, west
	 */
	public Position(int x, int y, Facing facingPosition) {
		this.xCoord = x;
		this.yCoord = y;
		this.facingPosition = facingPosition;
	}
	
	/**
	 * @return the xCoord
	 */
	public int getxCoord() {
		return xCoord;
	}
	/**
	 * @param xCoord the xCoord to set
	 */
	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	/**
	 * @return the yCoord
	 */
	public int getyCoord() {
		return yCoord;
	}
	/**
	 * @param yCoord the yCoord to set
	 */
	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	/**
	 * @return the facingPosition
	 */
	public Facing getFacingPosition() {
		return facingPosition;
	}
	/**
	 * @param facingPosition the facingPosition to set
	 */
	public void setFacingPosition(Facing facingPosition) {
		this.facingPosition = facingPosition;
	}

	/** 
	 * Prints the position according to the format X,Y,FACING
	 */
	@Override
	public String toString() {
		return xCoord + "," + yCoord + "," + facingPosition;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((facingPosition == null) ? 0 : facingPosition.hashCode());
		result = prime * result + xCoord;
		result = prime * result + yCoord;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Position))
			return false;
		Position other = (Position) obj;
		if (facingPosition != other.facingPosition)
			return false;
		if (xCoord != other.xCoord)
			return false;
		if (yCoord != other.yCoord)
			return false;
		return true;
	}
	
}
