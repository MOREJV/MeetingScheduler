package com.bank.meetingscheduler.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jotiram More
 * <pre>
 * <b>Class : </b> <br>
 * BookingGroup.
 * </pre>
 * <pre>
 * <b>Description : </b> <br>
 *BookingGroup DAO class.
 * </pre>
 */
public class BookingGroup
{


    String data;
    List<Booking> bookings = new ArrayList<>();
    
    /**
     * @return the data
     */
    public String getData ( )
    {
        return data;
    }

    
    /**
     * @param data the data to set
     */
    public void setData ( String data )
    {
        this.data = data;
    }

    
    /**
     * @return the bookings
     */
    public List < Booking > getBookings ( )
    {
        return bookings;
    }


    
    /**
     * @param bookings the bookings to set
     */
    public void setBookings ( List < Booking > bookings )
    {
        this.bookings = bookings;
    }


    
    
    
}
