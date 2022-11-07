package com.bank.meetingscheduler.exceptions;

/**
 * @author Jotiram More
 * <pre>
 * <b>Class : </b> <br>
 * MeetingSchedulerException.
 * </pre>
 * <pre>
 * <b>Description : </b> <br>
 * Custome execption class.
 * </pre>
 */
public class MeetingSchedulerException extends Exception
{
    public MeetingSchedulerException (String str)  
    {  
        // calling the constructor of parent Exception  
        super(str);  
    }  
}
