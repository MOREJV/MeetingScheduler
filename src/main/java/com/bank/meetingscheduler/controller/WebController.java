package com.bank.meetingscheduler.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.LocalDate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
 * WebController.
 * </pre>
 * <pre>
 * <b>Description : </b> <br>
 * Controller class for meeting Scheduler.
 * </pre>
 */

@RestController
public class WebController {

	@RequestMapping(Constants.FORWARD_SLASH+Constants.REQUEST_MAPPING_NAME)
	public List<BookingGroup> scheduleMeeting(@RequestParam(value = Constants.REQUEST_MAPPING_NAME) String meetingRequest) throws MeetingSchedulerException {
	    // MeetingScheduler
	    MeetingScheduler meetingScheduler = new MeetingScheduler(); 

	    //Schedule meeting
	    Map<LocalDate, Set<Meeting>> meetingsSchedule = meetingScheduler.scheduleMeeting (meetingRequest);

             // Construct BookingGroups and return the same
	   return BookingHelper.constructBookingGroups(meetingsSchedule);

	}
	

}