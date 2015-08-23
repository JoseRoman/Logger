package Logging;

/**
 * @author Ernie Wilson
 * 
 * This class contains the basics for what a logger requires
 */
public abstract class BaseLog implements ILog
{
    /** The name of the facility logging the info. */
    protected String facilityName;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void info(String message)
    {
        log(Severity.INFO, LogFormatting.createInfoLogData(Severity.INFO, facilityName, message));
    } 
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void debug(String message)
    {
        log(Severity.DEBUG, LogFormatting.createInfoLogData(Severity.DEBUG, facilityName, message));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void warning(String message)
    {
        log(Severity.WARNING, LogFormatting.createInfoLogData(Severity.WARNING, facilityName, message));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void error(String message, Exception ex)
    {
        log(Severity.ERROR, LogFormatting.createErrorLogData(facilityName, new LogData(message, ex)));
    }
    
    public abstract void log(Severity severity, Object[] message);
}