package com.bank.meetingscheduler.util;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import com.bank.meetingscheduler.constants.Constants;
import com.bank.meetingscheduler.models.Booking;
import com.bank.meetingscheduler.models.BookingGroup;
import com.bank.meetingscheduler.models.Meeting;

/**
 * @author Jotiram More
 * <pre>
 * <b>Class : </b> <br>
 * BookingHelper.
 * </pre>
 * <pre>
 * <b>Description : </b> <br>
 * MeetingScheduler Helper.
 * </pre>
 */
public class BookingHelper
{

    /**
     * <pre>
     * <b>Description : </b> <br>
     * Creates the BookingHelper.
     * </pre>
     */
    private BookingHelper ( )
    {
        // Prevent instantiation.
    }
    
    /**
     * <pre>
     * <b>Description : </b> <br>
     * Method to construct the BookingGroups.
     * @param meetingsSchedule : Map<LocalDate, Set<Meeting>>.
     * @return List<BookingGroup>.
     * </pre>
     */
    public static  List<BookingGroup> constructBookingGroups(  Map<LocalDate, Set<Meeting>> meetingsSchedule)
    {
        List<BookingGroup> updatedData = new  ArrayList<BookingGroup>();
        
       // let's sort this map by keys first
        Map<LocalDate, Set<Meeting>> srotedMeetingScheduled = new LinkedHashMap<>();
        meetingsSchedule.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> srotedMeetingScheduled.put(x.getKey(), x.getValue()));
        
        // get entrySet() into Set
        Set<Map.Entry<LocalDate, Set<Meeting>>> entrySet =
            srotedMeetingScheduled.entrySet();
 
        // Collection Iterator
        Iterator<Entry<LocalDate, Set<Meeting>>> iterator =
                entrySet.iterator();
 
        while(iterator.hasNext()) {
            BookingGroup bookingGroup = new BookingGroup();
            Entry<LocalDate, Set<Meeting>> entry = iterator.next();
            bookingGroup.setData ( entry.getKey().toString ( ) );
            List < Booking > bookingList = new  ArrayList <>();
            for(Meeting country : entry.getValue()) {
               
                Booking booking = new Booking();           
                booking.setEmp_id ( country.getEmpId ( ) );
                booking.setStart_time (  getHoursAndMinuts(country.getStartTime ( )) );
                booking.setEnd_time ( getHoursAndMinuts(country.getFinishTime ( )) );
                bookingList.add ( booking );
                bookingGroup.setBookings ( bookingList );
                
            }
            updatedData.add ( bookingGroup );
        }
        
        return updatedData;
        
    }

    /**
     * <pre>
     * <b>Description : </b> <br>
     * Method to construct the BookingGroups.
     * @param startTime : LocalTime.
     * @return String.
     * </pre>
     */
    private static String getHoursAndMinuts ( LocalTime startTime )
    {
       
        return  startTime.toString (Constants.PATTERN_HH_MM);       
    }
    


}
