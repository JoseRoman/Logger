package Logging;

/**
 * @author khsrobotics
 * 
 * Exception used for fatal errors when attempting to log
 */
public class LoggingException extends Exception {
	
	/**
	 * Exception used for fatal errors when attempting to log
	 * @param message info for the exception
	 */
	public LoggingException(String message) {
		super(message);
	}
}
