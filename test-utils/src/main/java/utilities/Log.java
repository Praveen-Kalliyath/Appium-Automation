package utilities;

import org.apache.log4j.Logger;

public class Log {
	/**
	 * @author Praveen Kalliyath
	 * 
	 * @version 1.0
	 * 
	 * @since June 30 2020
	 */
	private static Logger logger = Logger.getLogger(Log.class);

	/**
	 * Method to Log Information
	 * 
	 * @param message
	 */
	public static void info(String message) {
		logger.info(message);
	}

	/**
	 * Method to Log Warning
	 * 
	 * @param message
	 */
	public static void warn(String message) {
		logger.warn(message);
	}

	/**
	 * Method to Log Error
	 * 
	 * @param message
	 */
	public static void error(String message) {
		logger.error(message);
	}

	/**
	 * Method to Log Fatal
	 * 
	 * @param message
	 */
	public static void fatal(String message) {
		logger.fatal(message);
	}

	/**
	 * Method to Log Debug
	 * 
	 * @param message
	 */
	public static void debug(String message) {
		logger.debug(message);
	}
}
