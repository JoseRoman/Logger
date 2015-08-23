package Logging;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Ernie Wilson
 * 
 * This class is for formatting log messages
 */
public class LogFormatting 
{
	
	private LogFormatting() { }
	
	/**
	 * Formats a log message for info, debug or warning
	 * @param severity the severity
	 * @param facility the program logging
	 * @param message the information to log
	 * @return a formatted log message
	 */
	public static Object[] createInfoLogData(Severity severity, String facility, String message)
    {
        return new String[] {
            "Facility: " + facility,
            "Severity: " + severity.toString(),
            "Message: " + message,
            "Date: " + new Date(System.currentTimeMillis()).toString()
        };
    }
    
	/**
	 * Formats a log message for errors
	 * @param facility the program logging
	 * @param message the information to log
	 * @return a formatted error message
	 */
    public static Object[] createErrorLogData(String facility, LogData message)
    {
        ArrayList<String> temp = new ArrayList<String>();
        
        temp.add("Facility: " + facility + "-");
        temp.add("Severity: " + Severity.ERROR.toString() + "-");
        temp.add("Message: " + message.getMessage() + "-");
        
        String[] stacktrace = message.getStacktrace();
        
        for (int i = 0; i < stacktrace.length; i++)
        {
            if (i == 0)
                temp.add("Stacktrace: " + stacktrace[i]);
            else if (i == stacktrace.length)
                temp.add(stacktrace[i] + "-");
            else
                temp.add("            " + stacktrace[i]);
        }
        
        temp.add("Timestamp: " + message.getTimestamp() + "-");
        
        String[] data = new String[temp.size()];
        data = temp.toArray(data);
        
        String formated = "";
        for (String s : data)
        {
            formated += s;
        }
        
        return formated.split("-");
    }
}
