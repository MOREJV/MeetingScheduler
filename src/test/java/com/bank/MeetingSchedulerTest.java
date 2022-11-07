package com.bank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.LocalDate;
import org.junit.Test;

import com.bank.meetingscheduler.constants.Constants;
import com.bank.meetingscheduler.exceptions.MeetingSchedulerException;
import com.bank.meetingscheduler.models.BookingGroup;
import com.bank.meetingscheduler.models.Meeting;
import com.bank.meetingscheduler.service.MeetingScheduler;
import com.bank.meetingscheduler.util.BookingHelper;



/**
 * @author Jotiram More
 * <pre>
 * <b>Class : </b> <br>
 * MeetingSchedulerTest.
 * </pre>
 * <pre>
 * <b>Description : </b> <br>
 * MeetingScheduler Test.
 * </pre>
 */
public class MeetingSchedulerTest
{

    private String meetingRequest;

    /**
     * <pre>
     * <b>Description : </b> <br>
     * Method to setup any data before running the test classes.
     * </pre>
     */
    @org.junit.Before
    public void setUp ( ) throws Exception
    {
        // Nothing to do here at this time.

    }
    
    /**
     * <pre>
     * <b>Description : </b> <br>
     * Method to test Success scenario.
     * </pre>
     */
    @Test
    public void testSuccessMeetingScheduler ( )
    {
        meetingRequest = "0900 1730\n"
            + "2020-01-18 10:17:06 EMP001\n"
            + "2020-01-21 09:00 2\n"
            + "2020-01-18 12:34:56 EMP002\n"
            + "2020-01-21 09:00 2\n"
            + "2020-01-18 09:28:23 EMP003\n"
            + "2020-01-22 14:00 2\n"
            + "2020-01-18 11:23:45 EMP004\n"
            + "2020-01-22 16:00 1\n"
            + "2020-01-18 11:00:45 EMP006\n"
            + "2020-01-23 16:00 1\n"
            + "2020-01-15 11:00:45 EMP007\n"
            + "2020-01-23 15:00 2";
        // MeetingScheduler
        MeetingScheduler meetingScheduler = new MeetingScheduler ( );
        //List of Booking Groups
        List<BookingGroup> bookingGroups = new ArrayList<>();
 
            // Schedule meeting
            Map < LocalDate, Set < Meeting > > meetingsSchedule;
            try
            {
                meetingsSchedule = meetingScheduler.scheduleMeeting ( meetingRequest );
                // Construct Booking Groups
                bookingGroups = BookingHelper.constructBookingGroups(meetingsSchedule);
                assertNotNull ( bookingGroups );
            }
            catch ( MeetingSchedulerException e )
            {
                fail();
            }   
    }
    
    /**
     * <pre>
     * <b>Description : </b> <br>
     * Method to test invalid input for schedule meeting.
     * </pre>
     */
    @Test
    public void testInvalidInput ( )
    {
        meetingRequest = "";
        // MeetingScheduler
        MeetingScheduler meetingScheduler = new MeetingScheduler ( );

        try
        {
            // Schedule meeting
            Map < LocalDate, Set < Meeting > > meetingsSchedule = meetingScheduler.scheduleMeeting ( meetingRequest );
        }
        catch ( MeetingSchedulerException e )
        {
            assertEquals( e.getMessage ( ), Constants.INVALID_INPUT_ERROR);
            
        }

    }
    
    /**
     * <pre>
     * <b>Description : </b> <br>
     * Method to test success scenario with One outside hour meeting included.
     * </pre>
     */
    @Test
    public void testSuccessMeetingSchedulerWithIncludingOutsideHoursMeeting ( )
    {
        meetingRequest = "0900 1730\n"
            + "2020-01-18 10:17:06 EMP001\n"
            + "2020-01-21 09:00 2\n"
            + "2020-01-18 12:34:56 EMP002\n"
            + "2020-01-21 09:00 2\n"
            + "2020-01-18 09:28:23 EMP003\n"
            + "2020-01-22 14:00 2\n"
            + "2020-01-18 11:23:45 EMP004\n"
            + "2020-01-22 16:00 1\n"
            + "2020-01-15 17:29:12 EMP005\n"
            + "2020-01-21 16:00 3\n"
            + "2020-01-18 11:00:45 EMP006\n"
            + "2020-01-23 16:00 1\n"
            + "2020-01-15 11:00:45 EMP007\n"
            + "2020-01-23 15:00 2";
        // MeetingScheduler
        MeetingScheduler meetingScheduler = new MeetingScheduler ( );
        //List of Booking Groups
        List<BookingGroup> bookingGroups = new ArrayList<>();
 
            // Schedule meeting
            Map < LocalDate, Set < Meeting > > meetingsSchedule;
            try
            {
                meetingsSchedule = meetingScheduler.scheduleMeeting ( meetingRequest );
                // Constrcut Booking Groups
                bookingGroups = BookingHelper.constructBookingGroups(meetingsSchedule);
                assertNotNull ( bookingGroups );
            }
            catch ( MeetingSchedulerException e )
            {
                fail();
            }
  
    }
    
    /**
     * <pre>
     * <b>Description : </b> <br>
     * Method to test Fail scenario with OutSide hours .
     * </pre>
     */
    @Test
    public void testBookingOutSideHours ( )
    {
        meetingRequest = "0900 1730\n"
            + "2020-01-15 17:29:12 EMP005\n"
            + "2020-01-21 16:00 3";
        // MeetingScheduler
        MeetingScheduler meetingScheduler = new MeetingScheduler ( );

        try
        {
            // Schedule meeting
            Map < LocalDate, Set < Meeting > > meetingsSchedule = meetingScheduler.scheduleMeeting ( meetingRequest );
        }
        catch ( MeetingSchedulerException e )
        {
            assertEquals( e.getMessage ( ), "EMP005"+Constants.OUTSIDE_OFFICE_HOURS_ERROR);
            
        }

    }
    


}
