package com.bank.meetingscheduler.service;

import static java.lang.Integer.parseInt;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.util.StringUtils;

import com.bank.meetingscheduler.constants.Constants;
import com.bank.meetingscheduler.exceptions.MeetingSchedulerException;
import com.bank.meetingscheduler.models.Meeting;

/**
 * @author Jotiram More
 * <pre>
 * <b>Class : </b> <br>
 * MeetingScheduler.
 * </pre>
 * <pre>
 * <b>Description : </b> <br>
 * MeetingScheduler Main logic.
 * </pre>
 */
public class MeetingScheduler
{


    /**
     * <pre>
     * <b>Description : </b> <br>
     * Method to Schedule meeting.
     * @param meetingRequest :String.
     * @return Map < LocalDate, Set < Meeting > >.
     * </pre>
     * @throws MeetingSchedulerException 
     */
    public Map < LocalDate, Set < Meeting > > scheduleMeeting ( String meetingRequest ) throws MeetingSchedulerException
    {

        if ( meetingRequest == null || meetingRequest.isEmpty ( )   )
        {
            //Throw custome Exception
           throw new MeetingSchedulerException(Constants.INVALID_INPUT_ERROR);
        }
       
        String[ ] requestLines = meetingRequest.split (Constants.LINE_SEPERATOR);
        
        //Validate Basic Details :
        validateInputDetails(requestLines);

        String[ ] officeHoursTokens = requestLines[0].split (Constants.SPACE);
        LocalTime officeStartTime = new LocalTime ( parseInt ( officeHoursTokens[0].substring ( 0, 2 ) ),
            parseInt ( officeHoursTokens[0].substring ( 2, 4 ) ) );
        LocalTime officeFinishTime = new LocalTime ( parseInt ( officeHoursTokens[1].substring ( 0, 2 ) ),
            parseInt ( officeHoursTokens[1].substring ( 2, 4 ) ) );

        Map < LocalDate, Set < Meeting > > meetings = new HashMap < LocalDate, Set < Meeting > > ( );

        //Iterate requested lines to validate the meeting
        for ( int i = 1; i < requestLines.length; i = i + 2 )
        {

            String[ ] meetingSlotRequest = requestLines[i + 1].split (Constants.SPACE);
            LocalDate meetingDate = Constants.DATE_FORMAT.parseLocalDate ( meetingSlotRequest[0] );

            Meeting meeting = extractMeeting ( requestLines[i], officeStartTime, officeFinishTime, meetingSlotRequest );
            if ( meeting != null )
            {
                if ( meetings.containsKey ( meetingDate ) )
                {
                    meetings.get ( meetingDate ).remove ( meeting );
                    meetings.get ( meetingDate ).add ( meeting );
                }
                else
                {
                    Set < Meeting > meetingsForDay = new TreeSet < Meeting > ( );
                    meetingsForDay.add ( meeting );
                    meetings.put ( meetingDate, meetingsForDay );
                }
            }
        }
       if(meetings.isEmpty ( ) && !StringUtils.isEmpty ( outSideHourError ) )
       {
           throw new MeetingSchedulerException(outSideHourError);
       }
        return meetings;

    }
    
    private void validateInputDetails ( String[] meetingRequest ) throws MeetingSchedulerException
    {
        if(meetingRequest.length <=2) {
            //Throw custome Exception
            throw new MeetingSchedulerException(Constants.INVALID_INPUT_ERROR);
        }
        
    }

    /**
     * <pre>
     * <b>Description : </b> <br>
     * Method to Extract meeting.
     * @param requestLine :String.
     * @param officeStartTime :LocalTime.
     * @param officeFinishTime :officeStartTime.
     * @param meetingSlotRequest :String[].
     * @return Meeting.
     * </pre>
     * @throws MeetingSchedulerException 
     */
    private Meeting extractMeeting ( String requestLine, LocalTime officeStartTime, LocalTime officeFinishTime,
                                     String[ ] meetingSlotRequest ) throws MeetingSchedulerException
    {
        String[ ] employeeRequest = requestLine.split (Constants.SPACE );
        String employeeId = employeeRequest[2];

        LocalTime meetingStartTime = Constants.TIME_FORMAT.parseLocalTime ( meetingSlotRequest[1] );
        LocalTime meetingFinishTime =
            new LocalTime ( meetingStartTime.getHourOfDay ( ), meetingStartTime.getMinuteOfHour ( ) ).plusHours (
                parseInt ( meetingSlotRequest[2].trim ( ) ) );

        if ( meetingTimeOutsideOfficeHours ( officeStartTime, officeFinishTime, meetingStartTime, meetingFinishTime ) )
        {
          // Exception                        
            outSideHourError = employeeId+Constants.OUTSIDE_OFFICE_HOURS_ERROR;
            return null;
        }
        else
        {
            return new Meeting ( employeeId, meetingStartTime, meetingFinishTime );

        }
    }

    /**
     * <pre>
     * <b>Description : </b> <br>
     * Method to check if the meeting time is Outside office hours.
     * @param officeStartTime :LocalTime.
     * @param officeFinishTime :LocalTime.
     * @param meetingStartTime :LocalTime.
     * @param meetingFinishTime :LocalTime.
     * @param meetingSlotRequest :String[].
     * @return boolean.
     * </pre>
     */
    private boolean meetingTimeOutsideOfficeHours ( LocalTime officeStartTime, LocalTime officeFinishTime,
                                                    LocalTime meetingStartTime, LocalTime meetingFinishTime )
    {
        return meetingStartTime.isBefore ( officeStartTime ) || meetingStartTime.isAfter ( officeFinishTime )
            || meetingFinishTime.isAfter ( officeFinishTime ) || meetingFinishTime.isBefore ( officeStartTime );
    }


    //Local varaible to check error
    public String outSideHourError = Constants.EMPTY;
}
