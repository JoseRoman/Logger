package Logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Ernie Wilson
 * 
 * This class is used for logging to text files
 */
public class LocalLog extends BaseLog
{
    private File logFile;
    private boolean append;
    
    /**
     * Constructs a new LocalLog and writes to a a specified txt file
     * @param facilityName the name of the program using the logger
     * @param logFile the file to write to
     * @param append if true, the log will append the new logging info to the already existing
     * text file; if false, the log will delete the current log file and start in a fresh txt file
     * @throws IOException if the logger fails to create or delete the log file
     * @throws LoggingException if the file is not a text file
     */
    public LocalLog(String facilityName, File logFile, boolean append) throws IOException, LoggingException
    {
        if(logFile == null)
            throw new IllegalArgumentException("Log file cannot be null");
        if (facilityName == null)
            throw new IllegalArgumentException("Facility name cannot be null");
        
        if (!isTxtFile(logFile))
            throw new LoggingException("File for LocalLog isn't a txt file!");
        
        if(!logFile.exists())
        {
        	logFile.createNewFile();
        }
        else
        {
        	if(!append)
        	{
        		logFile.delete();
        		logFile.createNewFile();
        	}
        }
        
        this.facilityName = facilityName;
        this.logFile = logFile;
        this.append = append;
    }
    
    /**
     * Constructs a new LocalLog and writes to a a specified txt file
     * @param facilityName the name of the program using the logger
     * @param logFile the file to write to
     * @param append true to add to an existing text file, false to overwrite
     * @throws IOException if the logger fails to create or delete the log file
     * @throws LoggingException if the file is not a text file
     */
    public LocalLog(String facilityName, String logFile, boolean append) throws IOException, LoggingException
    {
    	this(facilityName, new File(logFile), append);
    }
    
    /**
     * Constructs a new LocalLog and writes to a a specified txt file
     * @param facilityName the name of the program using the logger
     * @param logFile the file to write to
     * @throws IOException if the logger fails to create or delete the log file
     * @throws LoggingException if the file is not a text file
     */
    public LocalLog(String facilityName, File logFile) throws IOException, LoggingException
    {
        this(facilityName, logFile, false);
    }
    
    /**
     * Constructs a new LocalLog and writes to a a specified txt file
     * @param facilityName the name of the program using the logger
     * @param logFile the file to write to
     * @throws IOException if the logger fails to create or delete the log file
     * @throws LoggingException if the file is not a text file
     */
    public LocalLog(String facilityName, String logFile) throws IOException, LoggingException
    {
    	this(facilityName, new File(logFile), false);
    }
    
    /**
     * Logs to a text file
     */
    @Override
    public void log(Severity severity, Object[] message)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(logFile, append));
            
            for (Object info : message)
            {
                bw.write(info.toString());
                bw.newLine();
            }
            
            bw.write("-------------------------------------------------------------------------------------------------------------------");
            bw.newLine();
            bw.close();
        }
        catch(Exception ex)
        {
            //How do to log when the log fails?!?!?
            System.err.println("Error while attempting to write logging information");
            ex.printStackTrace();
        }
    }
    
    /**
     * Determines if a specified file is a txt file.
     * @param file the file to check
     * @return true if the file is a txt file, false otherwise
     */
    private static boolean isTxtFile(File file) 
    {
        String ext = null;
        String s = file.getName();
        int i = s.lastIndexOf('.');
        
        if (i > 0 && i < s.length()-1)
            ext = s.substring(i+1).toLowerCase();
        
        return ext != null ? ext.equals("txt") : false;
    }
}