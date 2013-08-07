/**
 * @Copyright 2013
 * All rights reserved
 */
package com.winnegenic.reagroup.robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.winnegenic.reagroup.robot.command.Command;
import com.winnegenic.reagroup.robot.command.LeftCommand;
import com.winnegenic.reagroup.robot.command.MoveCommand;
import com.winnegenic.reagroup.robot.command.PlaceCommand;
import com.winnegenic.reagroup.robot.command.ReportCommand;
import com.winnegenic.reagroup.robot.command.RightCommand;

/**
 * CommandParserDelegate.java
 * @created 07/08/2013
 *
 */
public class CommandParserDelegate {

	private BufferedReader reader;
	private boolean isSensitiveParser; // if this flag is set to true, it will throw exceptions if unrecognised command is found

	private static final Logger log = Logger.getLogger(CommandParserDelegate.class);
	
	/**
	 * Constructs a parser from a reader, thus abstracting the source
	 * whether it be from standard input stream or from file input strea
	 * @param reader source of commands
	 * @param isSensitive indicates if this parser should throw up exceptions if unrecognised commands are found. if true, it will throw up, otherwise it will ignore
	 */
	public CommandParserDelegate(BufferedReader reader, boolean isSensitive) {
		this.reader = reader;
		this.isSensitiveParser = isSensitive;
	}
	
	/**
	 * This parses all the commands from the reader to generate the 
	 * command list to be returned
	 * @return
	 * @throws IOException 
	 */
	public List<Command> parseCommands() throws IOException {
		List<Command> commandList = new ArrayList<Command>();
		
		String line = "";
		int lineNumber = 1;
		while((line=reader.readLine()) != null) {
			log.info(line); // showing parsed line
			
			if (StringUtils.isEmpty(line)) {
				break; // empty lines will be treated as EOL 
			}
			
			String lineTrimmedUpper = line.trim().toUpperCase();
			
			Command parsedCommand = null;
			
			// parse single commands first
			if (lineTrimmedUpper.equalsIgnoreCase(Command.LEFT)) {
				parsedCommand = new LeftCommand();
			} else if (lineTrimmedUpper.equalsIgnoreCase(Command.RIGHT)) {
				parsedCommand = new RightCommand();
			} else if (lineTrimmedUpper.equalsIgnoreCase(Command.REPORT)) {
				parsedCommand = new ReportCommand();
			} else if (lineTrimmedUpper.equalsIgnoreCase(Command.MOVE)) {
				parsedCommand = new MoveCommand();
			} else if (lineTrimmedUpper.startsWith(Command.PLACE)){
				// need to parse a place command
				try {
					parsedCommand = parsePlaceCommand(lineTrimmedUpper);
				} catch (RuntimeException rte) {
					String errorMessage = "Unrecognised command found: line [" + lineNumber + "] " + line;
					log.error(errorMessage + rte);
					if (isSensitiveParser) {
						throw new IOException(errorMessage + rte);
					}
					continue;
				}
			} else {
				String errorMessage = "Unrecognised command found: line [" + lineNumber + "] " + line;
				log.error(errorMessage);
				if (isSensitiveParser) {
					throw new IOException(errorMessage);
				}
				continue; // otherwise ignore it!
			}
			
			commandList.add(parsedCommand); // add into the sequence
			lineNumber++;
		}
		
		return commandList;
	}

	/*
	 * Another delegate to parse the place component
	 */
	private Command parsePlaceCommand(String line) throws NoSuchElementException, NumberFormatException {
		String ingredients = line.substring(Command.PLACE.toString().length());
		
		StringTokenizer strTok = new StringTokenizer(ingredients, ","); // using comma as delimiter
		String xCoordString = strTok.nextToken().trim();
		String yCoordString = strTok.nextToken().trim();
		String faceString = strTok.nextToken().trim();
		
		int xCoord = Integer.valueOf(xCoordString);
		int yCoord = Integer.valueOf(yCoordString);
		
		Facing face = null;
		if (Facing.NORTH.toString().equalsIgnoreCase(faceString)) {
			face = Facing.NORTH;
		} else if (Facing.SOUTH.toString().equalsIgnoreCase(faceString)) {
			face = Facing.SOUTH;
		} else if (Facing.EAST.toString().equalsIgnoreCase(faceString)) {
			face = Facing.EAST;
		} else if (Facing.WEST.toString().equalsIgnoreCase(faceString)) {
			face = Facing.WEST;
		} else { // unknown face string
			throw new NoSuchElementException("Unknown facing element " + faceString);
		}
		
		Position pos = new Position(xCoord, yCoord, face);
		PlaceCommand placeCommand = new PlaceCommand(pos);
		
		return placeCommand;
	}
}
