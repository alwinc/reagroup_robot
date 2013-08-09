/**
 * @Copyright 2013
 * All rights reserved
 */
package com.winnergenic.reagroup.robot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import com.winnergenic.reagroup.robot.command.Command;

/**
 * RobotTests.java
 * @created 09/08/2013
 *
 */
public class RobotTests {

	private Robot robot;
	
	@Before
	public void setup() {
		robot = new Robot();
		Board board = new Board(5, 5);
		robot.setBoard(board);
	}
	
	/**
	 * Test1 from the specifications
	 */
	@Test
	public void sampleTest1() {
		StringBuilder commands = new StringBuilder();
		commands.append("PLACE 0,0,NORTH\n");
		commands.append("MOVE\n");
		commands.append("REPORT");
		
		List<Command> commandList = null;
		BufferedReader br = null;
		try {
			StringReader strReader = new StringReader(commands.toString());
			br = new BufferedReader(strReader);
			
			CommandParserDelegate parser = new CommandParserDelegate(br, false);
			commandList = parser.parseCommands();
		} catch (IOException e) {
			fail(); // should not happen as this is INSENSITIVE
		} finally {
			IOUtils.closeQuietly(br);
		}
		
		robot.setCommandSequence(commandList);
		robot.executeCommands();
		
		assertEquals(0, robot.getPosition().getxCoord());
		assertEquals(1, robot.getPosition().getyCoord());
		assertEquals(Facing.NORTH, robot.getPosition().getFacingPosition());
	}
	
	
	/**
	 * Test2 from the specifications
	 */
	@Test
	public void sampleTest2() {
		StringBuilder commands = new StringBuilder();
		commands.append("PLACE 0,0,NORTH\n");
		commands.append("LEFT\n");
		commands.append("REPORT");
		
		List<Command> commandList = null;
		BufferedReader br = null;
		try {
			StringReader strReader = new StringReader(commands.toString());
			br = new BufferedReader(strReader);
			
			CommandParserDelegate parser = new CommandParserDelegate(br, false);
			commandList = parser.parseCommands();
		} catch (IOException e) {
			fail(); // should not happen as this is INSENSITIVE
		} finally {
			IOUtils.closeQuietly(br);
		}
		
		robot.setCommandSequence(commandList);
		robot.executeCommands();
		
		assertEquals(0, robot.getPosition().getxCoord());
		assertEquals(0, robot.getPosition().getyCoord());
		assertEquals(Facing.WEST, robot.getPosition().getFacingPosition());
	}
	
	
	/**
	 * Test3 from the specifications
	 */
	@Test
	public void sampleTest3() {
		StringBuilder commands = new StringBuilder();
		commands.append("PLACE 1,2,EAST\n");
		commands.append("MOVE\n");
		commands.append("MOVE\n");
		commands.append("LEFT\n");
		commands.append("MOVE\n");
		commands.append("REPORT");
		
		List<Command> commandList = null;
		BufferedReader br = null;
		try {
			StringReader strReader = new StringReader(commands.toString());
			br = new BufferedReader(strReader);
			
			CommandParserDelegate parser = new CommandParserDelegate(br, false);
			commandList = parser.parseCommands();
		} catch (IOException e) {
			fail(); // should not happen as this is INSENSITIVE
		} finally {
			IOUtils.closeQuietly(br);
		}
		
		robot.setCommandSequence(commandList);
		robot.executeCommands();
		
		assertEquals(3, robot.getPosition().getxCoord());
		assertEquals(3, robot.getPosition().getyCoord());
		assertEquals(Facing.NORTH, robot.getPosition().getFacingPosition());
	}
	
	
	
	/**
	 * This is to ensure the robot ignores ALL commands PRIOR to the PLACE
	 * command
	 */
	@Test
	public void ignorePrePlaceCommands() {
		StringBuilder commands = new StringBuilder();
		// -- ignored commands
		commands.append("LEFT\n");
		commands.append("LEFT\n");
		commands.append("MOVE\n");
		commands.append("MOVE\n");
		commands.append("MOVE\n");
		commands.append("MOVE\n");
		// -- actual commands
		commands.append("PLACE 1,2,EAST\n");
		commands.append("MOVE\n");
		commands.append("MOVE\n");
		commands.append("LEFT\n");
		commands.append("MOVE\n");
		commands.append("REPORT");
		
		List<Command> commandList = null;
		BufferedReader br = null;
		try {
			StringReader strReader = new StringReader(commands.toString());
			br = new BufferedReader(strReader);
			
			CommandParserDelegate parser = new CommandParserDelegate(br, false);
			commandList = parser.parseCommands();
		} catch (IOException e) {
			fail(); // should not happen as this is INSENSITIVE
		} finally {
			IOUtils.closeQuietly(br);
		}
		
		assertEquals(12, commandList.size());
		
		robot.setCommandSequence(commandList);
		robot.executeCommands();
		
		assertEquals(3, robot.getPosition().getxCoord());
		assertEquals(3, robot.getPosition().getyCoord());
		assertEquals(Facing.NORTH, robot.getPosition().getFacingPosition());
	}
	
	

	/**
	 * This test to see if robot accepts multiple PLACE commands
	 */
	@Test
	public void duplicatedPlaceCommands() {
		StringBuilder commands = new StringBuilder();
		// -- ignored commands
		commands.append("LEFT\n");
		commands.append("MOVE\n");
		commands.append("MOVE\n");
		// -- actual commands
		commands.append("PLACE 1,2,EAST\n");
		commands.append("MOVE\n");
		commands.append("MOVE\n");
		commands.append("LEFT\n");
		commands.append("MOVE\n");
		commands.append("REPORT\n");
		// -- actual commands again!
		commands.append("PLACE 2,2,EAST\n");
		commands.append("MOVE\n");
		commands.append("MOVE\n");
		commands.append("LEFT\n");
		commands.append("MOVE\n");
		commands.append("REPORT");
		
		List<Command> commandList = null;
		BufferedReader br = null;
		try {
			StringReader strReader = new StringReader(commands.toString());
			br = new BufferedReader(strReader);
			
			CommandParserDelegate parser = new CommandParserDelegate(br, false);
			commandList = parser.parseCommands();
		} catch (IOException e) {
			fail(); // should not happen as this is INSENSITIVE
		} finally {
			IOUtils.closeQuietly(br);
		}
		
		assertEquals(15, commandList.size());
		
		robot.setCommandSequence(commandList);
		robot.executeCommands();
		
		assertEquals(4, robot.getPosition().getxCoord());
		assertEquals(3, robot.getPosition().getyCoord());
		assertEquals(Facing.NORTH, robot.getPosition().getFacingPosition());
	}
	
	
	/**
	 * This test to see if robot accepts multiple PLACE commands
	 */
	@Test
	public void duplicatedPlaceOffTheBoardCommands() {
		StringBuilder commands = new StringBuilder();
		// -- ignored commands
		commands.append("LEFT\n");
		commands.append("MOVE\n");
		commands.append("MOVE\n");
		// -- actual commands
		commands.append("PLACE 6,3,EAST\n");
		commands.append("MOVE\n");
		commands.append("MOVE\n");
		commands.append("LEFT\n");
		commands.append("MOVE\n");
		commands.append("REPORT\n");
		// -- actual commands again!
		commands.append("PLACE 1,8,EAST\n");
		commands.append("MOVE\n");
		commands.append("MOVE\n");
		commands.append("LEFT\n");
		commands.append("MOVE\n");
		commands.append("REPORT");
		
		List<Command> commandList = null;
		BufferedReader br = null;
		try {
			StringReader strReader = new StringReader(commands.toString());
			br = new BufferedReader(strReader);
			
			CommandParserDelegate parser = new CommandParserDelegate(br, false);
			commandList = parser.parseCommands();
		} catch (IOException e) {
			fail(); // should not happen as this is INSENSITIVE
		} finally {
			IOUtils.closeQuietly(br);
		}
		
		assertEquals(15, commandList.size());
		
		robot.setCommandSequence(commandList);
		robot.executeCommands();
		
		assertNull(robot.getPosition());
	}
	
	
	/**
	 * This test to see if robot accepts multiple PLACE commands
	 */
	@Test
	public void extensiveCommandsTest() {
		StringBuilder commands = new StringBuilder();
		// -- ignored commands
		commands.append("LEFT\n");
		commands.append("MOVE\n");
		commands.append("MOVE\n");
		// -- actual commands
		commands.append("PLACE 1,0,EAST\n");
		commands.append("MOVE\n");
		commands.append("MOVE\n");
		commands.append("LEFT\n");
		commands.append("MOVE\n");
		commands.append("REPORT\n");
		// -- actual commands again!
		commands.append("PLACE 2,2,EAST\n");
		commands.append("MOVE\n");
		commands.append("MOVE\n");
		commands.append("LEFT\n");
		commands.append("MOVE\n");
		commands.append("REPORT");
		// -- try to move bot off the table
		commands.append("MOVE\n");
		commands.append("MOVE\n");
		commands.append("MOVE\n");
		commands.append("MOVE\n");
		commands.append("MOVE\n");
		commands.append("MOVE\n");
		commands.append("MOVE\n");
		commands.append("MOVE\n");
		commands.append("RIGHT\n");
		commands.append("REPORT");
		
		List<Command> commandList = null;
		BufferedReader br = null;
		try {
			StringReader strReader = new StringReader(commands.toString());
			br = new BufferedReader(strReader);
			
			CommandParserDelegate parser = new CommandParserDelegate(br, false);
			commandList = parser.parseCommands();
		} catch (IOException e) {
			fail(); // should not happen as this is INSENSITIVE
		} finally {
			IOUtils.closeQuietly(br);
		}
		
		assertEquals(23, commandList.size());
		
		robot.setCommandSequence(commandList);
		robot.executeCommands();
		
		assertEquals(4, robot.getPosition().getxCoord());
		assertEquals(4, robot.getPosition().getyCoord());
		assertEquals(Facing.EAST, robot.getPosition().getFacingPosition());
	}
}
