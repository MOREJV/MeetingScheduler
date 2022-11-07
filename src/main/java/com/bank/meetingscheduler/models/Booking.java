package com.bank.meetingscheduler.models;

/**
 * @author Jotiram More
 * <pre>
 * <b>Class : </b> <br>
 * Booking.
 * </pre>
 * <pre>
 * <b>Description : </b> <br>
 *Booking DAO class.
 * </pre>
 */
public class Booking
{

    
    String emp_id;
    String start_time;
    String end_time;
    
    /**
     * @return the emp_id
     */
    public String getEmp_id ( )
    {
        return emp_id;
    }
    
    /**
     * @param emp_id the emp_id to set
     */
    public void setEmp_id ( String emp_id )
    {
        this.emp_id = emp_id;
    }
    
    /**
     * @return the start_time
     */
    public String getStart_time ( )
    {
        return start_time;
    }
    
    /**
     * @param start_time the start_time to set
     */
    public void setStart_time ( String start_time )
    {
        this.start_time = start_time;
    }
    
    /**
     * @return the end_time
     */
    public String getEnd_time ( )
    {
        return end_time;
    }
    
    /**
     * @param end_time the end_time to set
     */
    public void setEnd_time ( String end_time )
    {
        this.end_time = end_time;
    }


    
}
