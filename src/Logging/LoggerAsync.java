package Logging;

/**
 * 
 * @author Ernie Wilson
 * 
 * Logs to a specified text file asynchronously.
 */
public class LoggerAsync implements ILog
{
	private volatile ILog log;
	
	/**
	 * Constructs a logger that will log asynchronously 
	 * @param log the logger
	 */
	public LoggerAsync(LocalLog log)
	{
		this.log = log;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public synchronized void info(String message)
	{
		new LoggingThread(log, Severity.INFO, message, null).start();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public synchronized void debug(String message)
	{
		new LoggingThread(log, Severity.DEBUG, message, null).start();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public synchronized void warning(String message)
	{
		new LoggingThread(log, Severity.WARNING, message, null).start();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public synchronized void error(String message, Exception ex)
	{
		new LoggingThread(log, Severity.ERROR, message, ex).start();
	}
	
	/**
	 * The magic behind this class...
	 */
	private class LoggingThread extends Thread implements Runnable
	{
		private ILog log;
		private Severity severity;
		private String message;
		private Exception ex;
		
		/**
		 * Creates a thread for logging to make logging async
		 * @param log the logger to execute the logging function on
		 * @param severity info = 0, debug = 1, warning = 2, error = 3
		 */
		public LoggingThread(ILog log, Severity severity, String mssg, Exception ex)
		{
			this.log = log;
			this.severity = severity;
			this.message = mssg;
			this.ex = ex;
		}
		
		@Override
		public void run()
		{
			switch(severity)
			{
				case INFO:
					log.info(message);
				break;
				
				case DEBUG:
					log.debug(message);
				break;
				
				case WARNING:
					log.warning(message);
				break;
				
				case ERROR:
					log.error(message, ex);
				break;
			}
		}
	}
}
