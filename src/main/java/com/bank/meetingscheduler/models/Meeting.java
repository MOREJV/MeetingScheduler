package com.bank.meetingscheduler.models;
import org.joda.time.Interval;
import org.joda.time.LocalTime;


/**
 * @author Jotiram More
 * <pre>
 * <b>Class : </b> <br>
 * Meeting.
 * </pre>
 * <pre>
 * <b>Description : </b> <br>
 * DAO class for Meeting.
 * </pre>
 */
public class Meeting  implements Comparable<Meeting>  {

	private String empId;

	private LocalTime requestStartTime;

	private LocalTime requestFinishTime;

	public Meeting(String empId, LocalTime startTime, LocalTime finishTime) {
		this.empId = empId;
		this.requestStartTime = startTime;
		this.requestFinishTime = finishTime;
	}

	public String getEmpId() {
		return empId;
	}

	public LocalTime getStartTime() {
		return requestStartTime;
	}

	public LocalTime getFinishTime() {
		return requestFinishTime;
	}
	
        public int compareTo(Meeting that) {
            Interval meetingInterval = new Interval(requestStartTime.toDateTimeToday(),
                            requestFinishTime.toDateTimeToday());
            Interval toCompareMeetingInterval = new Interval(that.getStartTime()
                            .toDateTimeToday(), that.getFinishTime().toDateTimeToday());

            if (meetingInterval.overlaps(toCompareMeetingInterval)) {
                    return 0;
            } else {
                    return this.getStartTime().compareTo(that.getStartTime());
            }

    }

}
