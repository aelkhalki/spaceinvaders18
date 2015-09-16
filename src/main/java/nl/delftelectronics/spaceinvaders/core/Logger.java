/**
 * 
 */
package nl.delftelectronics.spaceinvaders.core;

import java.io.PrintStream;

import org.joda.time.DateTime;

/**
 * @author Max
 * Handles centralized logging for the application.
 * By default logs go to standard out but this can be set to a different stream.
 */
public final class Logger {
	private static Object lockObject = new Object();
	private static LogLevel logLevel = LogLevel.Default;
	private static PrintStream outputStream = System.out; 
	
	/**
	 * Non usable constructor, is a static class
	 */
	private Logger() { }
	
	/**
	 * Logs a message with a specified LogLevel
	 * @param logLevel  The LogLevel of the message
	 * @param message   A format string specifying the message
	 * @param args      The arguments to use in the message
	 */
	public static void log(LogLevel logLevel, String message, Object... args) {
		if (logLevel == null) {
			throw new IllegalArgumentException("Argument logLevel should not be null");
		}
		if (message == null) {
			throw new IllegalArgumentException("Argument message should not be null");
		}
		
		synchronized (lockObject) {
			if (logLevel.level < Logger.logLevel.level) {
				return;
			}
			
			String output = String.format(message, args);
			outputStream.print(DateTime.now());
			outputStream.print(" ");
			outputStream.print(logLevel);
			outputStream.print(": ");
			outputStream.println(output);
		}
	}
	
	/**
	 * Redirects log output to a specified stream.
	 * @param stream  The new output stream to use
	 */
	public static void setOutputStream(PrintStream stream) {
		if (stream == null) {
			throw new IllegalArgumentException("Argument stream should not be null");
		}
		
		synchronized (lockObject) {
			outputStream = stream;
		}
	}
	
	/**
	 * Gets the current lowest LogLevel that will be logged.
	 * @return The current LogLevel
	 */
	public static LogLevel getLogLevel() {
		synchronized (lockObject) {
			return logLevel;
		}
	}
	
	/**
	 * Sets the minimum LogLevel to a value
	 * @param logLevel  The value to set the minimum LogLevel to
	 */
	public static void setLogLevel(LogLevel logLevel) {
		if (logLevel == null) {
			throw new IllegalArgumentException("Argument logLevel should not be null");
		}
		
		synchronized (lockObject) {
			Logger.logLevel = logLevel;
		}
	}
	
	/**
	 * Represents the severity of a message.
	 * @author Max
	 *
	 */
	public enum LogLevel {
		Debug(0),
		Info(100),
		Default(200),
		Warning(300),
		Error(400),
		None(1000);
		
		int level;
		/**
		 * Creates a new LogLevel with an integer value specifying it's severity
		 * @param level  The severity of this LogLevel
		 */
		LogLevel(int level) {
			this.level = level;
		}
	}
}
