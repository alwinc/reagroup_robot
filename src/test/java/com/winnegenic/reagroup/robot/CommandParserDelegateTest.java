/**
 * @Copyright 2013
 * All rights reserved
 */
package com.winnegenic.reagroup.robot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.winnegenic.reagroup.robot.command.Command;
import com.winnegenic.reagroup.robot.command.LeftCommand;
import com.winnegenic.reagroup.robot.command.MoveCommand;
import com.winnegenic.reagroup.robot.command.PlaceCommand;
import com.winnegenic.reagroup.robot.command.ReportCommand;
import com.winnegenic.reagroup.robot.command.RightCommand;

/**
 * CommandParserDelegateTest.java
 * @created 07/08/2013
 *
 */
public class CommandParserDelegateTest {
	
	/*
	 * Ensuring we are able to parse a PLACE COMMAND
	 */
	@Test
	public void testPlaceCommandParsingInSensitive() {
		StringBuilder sb = new StringBuilder();
		sb.append("PLACE 0,0,NORTH\n");
		
		BufferedReader input = null;
		try {
			StringReader strReader = new StringReader(sb.toString());
			input = new BufferedReader(strReader);
			
			CommandParserDelegate parser = new CommandParserDelegate(input, false);
			List<Command> commandList = parser.parseCommands();
			
			assertEquals(1, commandList.size());
			
			// check place command
			Command c = commandList.get(0);
			assertTrue(c instanceof PlaceCommand);
			assertEquals(0, ((PlaceCommand)c).getPlacePosition().getxCoord());
			assertEquals(0, ((PlaceCommand)c).getPlacePosition().getyCoord());
			assertEquals(Facing.NORTH, ((PlaceCommand)c).getPlacePosition().getFacingPosition());
		} catch (IOException e) {
			fail(); // should not happen as this is INSENSITIVE
		} finally {
			IOUtils.closeQuietly(input);
		}
	}
	
	// parsing a MOVE command
	@Test
	public void testMoveCommandParsingInSensitive() {
		StringBuilder sb = new StringBuilder();
		sb.append("MOVE");
		
		BufferedReader input = null;
		try {
			StringReader strReader = new StringReader(sb.toString());
			input = new BufferedReader(strReader);
			
			CommandParserDelegate parser = new CommandParserDelegate(input, false);
			List<Command> commandList = parser.parseCommands();
			
			assertEquals(1, commandList.size());
			
			// check place command
			Command c = commandList.get(0);
			assertTrue(c instanceof MoveCommand);
		} catch (IOException e) {
			fail(); // should not happen as this is INSENSITIVE
		} finally {
			IOUtils.closeQuietly(input);
		}
	}
	
	// parsing a left command
	@Test
	public void testLeftCommandParsingInSensitive() {
		StringBuilder sb = new StringBuilder();
		sb.append("LEFT");
		
		BufferedReader input = null;
		try {
			StringReader strReader = new StringReader(sb.toString());
			input = new BufferedReader(strReader);
			
			CommandParserDelegate parser = new CommandParserDelegate(input, false);
			List<Command> commandList = parser.parseCommands();
			
			assertEquals(1, commandList.size());
			
			// check place command
			Command c = commandList.get(0);
			assertTrue(c instanceof LeftCommand);
		} catch (IOException e) {
			fail(); // should not happen as this is INSENSITIVE
		} finally {
			IOUtils.closeQuietly(input);
		}
	}
	
	// parsing a Right command
	@Test
	public void testRightCommandParsingInSensitive() {
		StringBuilder sb = new StringBuilder();
		sb.append("RIGHT");
		
		BufferedReader input = null;
		try {
			StringReader strReader = new StringReader(sb.toString());
			input = new BufferedReader(strReader);
			
			CommandParserDelegate parser = new CommandParserDelegate(input, false);
			List<Command> commandList = parser.parseCommands();
			
			assertEquals(1, commandList.size());
			
			// check place command
			Command c = commandList.get(0);
			assertTrue(c instanceof RightCommand);
		} catch (IOException e) {
			fail(); // should not happen as this is INSENSITIVE
		} finally {
			IOUtils.closeQuietly(input);
		}
	}
	
	// parsing a REPORT command
	@Test
	public void testReportCommandParsingInSensitive() {
		StringBuilder sb = new StringBuilder();
		sb.append("REPORT");
		
		BufferedReader input = null;
		try {
			StringReader strReader = new StringReader(sb.toString());
			input = new BufferedReader(strReader);
			
			CommandParserDelegate parser = new CommandParserDelegate(input, false);
			List<Command> commandList = parser.parseCommands();
			
			assertEquals(1, commandList.size());
			
			// check place command
			Command c = commandList.get(0);
			assertTrue(c instanceof ReportCommand);
		} catch (IOException e) {
			fail(); // should not happen as this is INSENSITIVE
		} finally {
			IOUtils.closeQuietly(input);
		}
	}
	
	/*
	 * Ensuring we are able to parse a PLACE COMMAND
	 */
	@Test
	public void testPlaceCommandSpaceParsingInSensitive() {
		StringBuilder sb = new StringBuilder();
		sb.append("            PLACE                 0,           0,             NorTh\n");
		
		BufferedReader input = null;
		try {
			StringReader strReader = new StringReader(sb.toString());
			input = new BufferedReader(strReader);
			
			CommandParserDelegate parser = new CommandParserDelegate(input, false);
			List<Command> commandList = parser.parseCommands();
			
			assertEquals(1, commandList.size());
			
			// check place command
			Command c = commandList.get(0);
			assertTrue(c instanceof PlaceCommand);
			assertEquals(0, ((PlaceCommand)c).getPlacePosition().getxCoord());
			assertEquals(0, ((PlaceCommand)c).getPlacePosition().getyCoord());
			assertEquals(Facing.NORTH, ((PlaceCommand)c).getPlacePosition().getFacingPosition());
		} catch (IOException e) {
			fail(); // should not happen as this is INSENSITIVE
		} finally {
			IOUtils.closeQuietly(input);
		}
	}

	/*
	 * Simple test to ensure 3 commands are parsed correctly
	 * generating 3 commands in list and place values are correct
	 */
	@Test
	public void testSampleInput1InSensitive() {
		StringBuilder sb = new StringBuilder();
		sb.append("PLACE 0,0,NORTH\n");
		sb.append("MOVE\n");
		sb.append("REPORT\n");
		
		BufferedReader input = null;
		try {
			StringReader strReader = new StringReader(sb.toString());
			input = new BufferedReader(strReader);
			
			CommandParserDelegate parser = new CommandParserDelegate(input, false);
			List<Command> commandList = parser.parseCommands();
			
			assertEquals(3, commandList.size());
			
			// check place command
			Command c = commandList.get(0);
			assertTrue(c instanceof PlaceCommand);
			assertEquals(0, ((PlaceCommand)c).getPlacePosition().getxCoord());
			assertEquals(0, ((PlaceCommand)c).getPlacePosition().getyCoord());
			assertEquals(Facing.NORTH, ((PlaceCommand)c).getPlacePosition().getFacingPosition());
			
			// check move command
			c = commandList.get(1);
			assertTrue(c instanceof MoveCommand);
			
			// check report command
			c = commandList.get(2);
			assertTrue(c instanceof ReportCommand);
			
		} catch (IOException e) {
			fail(); // should not happen as this is INSENSITIVE
		} finally {
			IOUtils.closeQuietly(input);
		}
	}
	
	/*
	 * Larger command list testing
	 */
	@Test
	public void testLargerCommandsInSensitive() {
		StringBuilder sb = new StringBuilder();
		sb.append("PLACE 0,0,NORTH\n");
		sb.append("MOVE\n");
		sb.append("MOVE\n");
		sb.append("MOVE\n");
		sb.append("LEFT\n");
		sb.append("MOVE\n");
		sb.append("MOVE\n");
		sb.append("RIGHT\n");
		sb.append("REPORT\n");
		sb.append("PLACE 0,0,NORTH\n");
		sb.append("MOVE\n");
		sb.append("MOVE\n");
		sb.append("MOVE\n");
		sb.append("LEFT\n");
		sb.append("MOVE\n");
		sb.append("MOVE\n");
		sb.append("RIGHT\n");
		sb.append("REPORT\n");
		
		BufferedReader input = null;
		try {
			StringReader strReader = new StringReader(sb.toString());
			input = new BufferedReader(strReader);
			
			CommandParserDelegate parser = new CommandParserDelegate(input, false);
			List<Command> commandList = parser.parseCommands();
			
			assertEquals(18, commandList.size());
			
		} catch (IOException e) {
			fail(); // should not happen as this is INSENSITIVE
		} finally {
			IOUtils.closeQuietly(input);
		}
	}
	
	/**
	 * Testing Case sensitivity
	 */
	@Test
	public void testLargerCaseInSensitive() {
		StringBuilder sb = new StringBuilder();
		sb.append("PlAcE 0,0,NoRtH\n");
		sb.append("MoVe\n");
		sb.append("mOvE\n");
		sb.append("move\n");
		sb.append("lEfT\n");
		sb.append("MoVe\n");
		sb.append("move\n");
		sb.append("RiGhT\n");
		sb.append("rEpORT\n");
		sb.append("PLACE 0,0,NORTH\n");
		sb.append("Move\n");
		sb.append("moVE\n");
		sb.append("MovE\n");
		sb.append("lEFt\n");
		sb.append("moVe\n");
		sb.append("mOve\n");
		sb.append("riGHT\n");
		sb.append("RePOrT\n");
		
		BufferedReader input = null;
		try {
			StringReader strReader = new StringReader(sb.toString());
			input = new BufferedReader(strReader);
			
			CommandParserDelegate parser = new CommandParserDelegate(input, false);
			List<Command> commandList = parser.parseCommands();
			
			assertEquals(18, commandList.size());
			
		} catch (IOException e) {
			fail(); // should not happen as this is INSENSITIVE
		} finally {
			IOUtils.closeQuietly(input);
		}
	}
	
	
	/**
	 * Testing Error parsing with insensitivity
	 * This should continue on with a log and ignoring the line
	 */
	@Test
	public void testErrorParsingInSensitive() {
		StringBuilder sb = new StringBuilder();
		sb.append("PlAcE 0,0,NoRtH\n");
		sb.append("MoVe\n");
		sb.append("mOvE\n");
		sb.append("move\n");
		sb.append("lEfT\n");
		sb.append("MoVe\n");
		sb.append("move\n");
		sb.append("RiGhtT\n"); // <--- here is the error
		sb.append("rEpORT\n");
		sb.append("PLACE 0,0,NORTH\n");
		sb.append("Move\n");
		sb.append("moVE\n");
		sb.append("MovE\n");
		sb.append("lEFt\n");
		sb.append("moVe\n");
		sb.append("mOve\n");
		sb.append("riGHT\n");
		sb.append("RePOrT\n");
		
		BufferedReader input = null;
		try {
			StringReader strReader = new StringReader(sb.toString());
			input = new BufferedReader(strReader);
			
			CommandParserDelegate parser = new CommandParserDelegate(input, false);
			List<Command> commandList = parser.parseCommands();
			
			assertEquals(17, commandList.size());
			
			assertTrue(commandList.get(6) instanceof MoveCommand);
			assertTrue(commandList.get(7) instanceof ReportCommand); // right should have been ignored
			
		} catch (IOException e) {
			fail(); // should not happen as this is INSENSITIVE
		} finally {
			IOUtils.closeQuietly(input);
		}
	}
	
	
	/**
	 * Testing Error parsing with sensitivity
	 * This should throw up an error as the line is not recognised
	 * @throws IOException 
	 */
	@Test(expected=IOException.class)
	public void testErrorParsingSensitive() throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("PlAcE 0,0,NoRtH\n");
		sb.append("MoVe\n");
		sb.append("mOvE\n");
		sb.append("move\n");
		sb.append("lEfT\n");
		sb.append("MoVe\n");
		sb.append("move\n");
		sb.append("RiGhtT\n"); // <--- here is the error
		sb.append("rEpORT\n");
		sb.append("PLACE 0,0,NORTH\n");
		sb.append("Move\n");
		sb.append("moVE\n");
		sb.append("MovE\n");
		sb.append("lEFt\n");
		sb.append("moVe\n");
		sb.append("mOve\n");
		sb.append("riGHT\n");
		sb.append("RePOrT\n");
		
		BufferedReader input = null;
		try {
			StringReader strReader = new StringReader(sb.toString());
			input = new BufferedReader(strReader);
			
			CommandParserDelegate parser = new CommandParserDelegate(input, true); // changed to sensitive
			parser.parseCommands();
		} finally {
			IOUtils.closeQuietly(input);
		}
	}
	
	/**
	 * Testing Error parsing with sensitivity
	 * This should throw up an error with place command going bizerk
	 */
	@Test(expected=IOException.class)
	public void testErrorParsingPlaceSensitive() throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("PlAcE 0.78ad,0,NoRtH\n");
		
		BufferedReader input = null;
		try {
			StringReader strReader = new StringReader(sb.toString());
			input = new BufferedReader(strReader);
			
			CommandParserDelegate parser = new CommandParserDelegate(input, true); // changed to sensitive
			parser.parseCommands();
		} finally {
			IOUtils.closeQuietly(input);
		}
	}
	
	/**
	 * Testing Error parsing with sensitivity
	 * This should throw up an error with place command going bizerk
	 */
	@Test(expected=IOException.class)
	public void testErrorParsingPlace2Sensitive() throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("PlAcE 1,0,TIMBUKTU\n");
		
		BufferedReader input = null;
		try {
			StringReader strReader = new StringReader(sb.toString());
			input = new BufferedReader(strReader);
			
			CommandParserDelegate parser = new CommandParserDelegate(input, true); // changed to sensitive
			parser.parseCommands();
		} finally {
			IOUtils.closeQuietly(input);
		}
	}
}
