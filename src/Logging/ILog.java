package Logging;

/**
 * @author Ernie Wilson
 * 
 * This interface contains the basic methods of the logs
 */
public interface ILog 
{
	/**
	 * Method used to log informational messages
	 * @param message the information to the informational log
	 */
    public void info(String message);
    
    /**
     * Method used to log debugging messages
     * @param message the information for the debug log
     */
    public void debug(String message);
    
    /**
     * Method used to log warning messages
     * @param message the information for the warning log
     */
    public void warning(String message);
    
    /**
     * Method used to log error messages
     * @param message the information for the error log
     * @param ex the exception involved in the error
     */
    public void error(String message, Exception ex);
}