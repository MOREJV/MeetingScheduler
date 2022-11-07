package com.bank.meetingscheduler.constants;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author Jotiram More
 * <pre>
 * <b>Class : </b> <br>
 * CommonConstants.
 * </pre>
 * <pre>
 * <b>Description : </b> <br>
 * Constants for Meeting Scheduler.
 * </pre>
 */
public class Constants

{
    
    /**
     * <pre>
     * <b>Description : </b> <br>
     * Creates the CommonConstants.
     * </pre>
     */
    private Constants ( )
    {
        // Prevent instantiation.
    }
    
    /**
     * PATTERN_HH_MM.
     */
    public static final String PATTERN_HH_MM = "HH:mm";
    
    
    /**
     * PATTERN_YYYY_MM_DD.
     */
    public static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
    
   
    /**
     * DATE_FORMAT.
     */
    public static DateTimeFormatter DATE_FORMAT = DateTimeFormat
        .forPattern(PATTERN_YYYY_MM_DD);
    
    /**
     * TIME_FORMAT.
     */
    public static DateTimeFormatter TIME_FORMAT = DateTimeFormat
        .forPattern(PATTERN_HH_MM);

    /**
     * LINE_SEPERATOR.
     */
    public static final String LINE_SEPERATOR= "\\n" ;


    /**
     * SPACE.
     */
    public static final String SPACE = " ";
    
    /**
     * EMPTY.
     */
    public static final String EMPTY = "";
    
    
    /**
     * INVALID_INPUT_ERROR.
     */
    public static final String INVALID_INPUT_ERROR = "Employee has requested for booking is not a valid input";  
    
    /**
     * OUTSIDE_OFFICE_HOURS_ERROR.
     */
    public static final String OUTSIDE_OFFICE_HOURS_ERROR = " has requested booking which is outside office hour."; 
    
    
    /**
     * REQUEST_MAPPING_NAME.
     */
    public static final String REQUEST_MAPPING_NAME = "meetingRequest";   
    
    
    /**
     * FORWARD_SLASH.
     */
    public static final String FORWARD_SLASH = "/";  

}
