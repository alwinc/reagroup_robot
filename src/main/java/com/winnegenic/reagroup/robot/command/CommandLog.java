/**
 * @Copyright 2013
 * All rights reserved
 */
package com.winnegenic.reagroup.robot.command;

/**
 * CommandLog.java
 * Started - Aug 7, 2013
 */
public class CommandLog {
	private boolean isCommandInQuestion;
	private String statusReason; // only requires a reason if command is in question
	
	// basic command log for successful/no-problems command
	public static final CommandLog OKCOMMANDLOG = new CommandLog(false, null);
	
	public CommandLog(boolean isQuestionable, String reason) {
		this.isCommandInQuestion = isQuestionable;
		statusReason = reason;
	}

	/**
	 * @return the isCommandInQuestion
	 */
	public boolean isCommandInQuestion() {
		return isCommandInQuestion;
	}

	/**
	 * @param isCommandInQuestion the isCommandInQuestion to set
	 */
	public void setCommandInQuestion(boolean isCommandInQuestion) {
		this.isCommandInQuestion = isCommandInQuestion;
	}

	/**
	 * @return the statusReason
	 */
	public String getStatusReason() {
		return statusReason;
	}

	/**
	 * @param statusReason the statusReason to set
	 */
	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}
	
	
}
