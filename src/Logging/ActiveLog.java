package Logging;

/**
 * @author Ernie Wilson
 * 
 * This class is for logging to a text file without constructing an instance of
 * LocalLog.java. 
 */
public final class ActiveLog 
{
	private ActiveLog() { }
	
	private static final Object lock = new Object();
	
	/**
	 * Method used to log informational messages
	 * @param path the text file path to log to
	 * @param facility the program logging
	 * @param message the information to log
	 */
	public static void info(String path, String facility, String message) 
	{
		log(path, Severity.INFO, facility, message, null);
	}
	
	/**
	 * Method used to log debugging messages
	 * @param path the text file path to log to
	 * @param facility the program logging
	 * @param message the information to log
	 */
	public static void debug(String path, String facility, String message) 
	{
		log(path, Severity.DEBUG, facility, message, null);
	}
	
	/**
	 * Method used to log warning messages
	 * @param path the text file path to log to
	 * @param facility the program logging
	 * @param message the information to log
	 */
	public static void warning(String path, String facility, String message) 
	{
		log(path, Severity.WARNING, facility, message, null);
	}
	
	/**
	 * Method used to log error messages
	 * @param path the text file path to log to
	 * @param facility the program logging
	 * @param message the information to log
	 * @param ex the exception involved in the error
	 */
	public static void error(String path, String facility, String message, Exception ex) 
	{
		log(path, Severity.ERROR, facility, message, ex);
	}
	
	private static void log(String path, Severity severity, String facility, String message, Exception ex) 
	{
		synchronized(lock) 
		{
			try 
			{
				LocalLog localLog = new LocalLog(facility, path, true);
				LoggerAsync log = new LoggerAsync(localLog);
				
				if(severity == Severity.ERROR) 
				{
					log.error(message, ex);
				}
				else if(severity == Severity.INFO) 
				{
					log.info(message);
				} 
				else if(severity == Severity.DEBUG)
				{
					log.debug(message);
				}
				else if(severity == Severity.WARNING)
				{
					log.warning(message);
				} 
				else
				{
					throw new LoggingException("Invalid severity in ActiveLog.java");
				}
				
			} 
			catch (Exception e) 
			{
				System.err.println(e.getClass().getSimpleName() + " in ActiveLog");
			}
		}
	}
}
