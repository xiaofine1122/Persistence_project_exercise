package com.xiaofine.meeting.service;

import com.xiaofine.meeting.dao.MeetingDao;
import com.xiaofine.meeting.dao.MeetingParticipantsDao;
import com.xiaofine.meeting.pojo.Meeting;
import com.xiaofine.meeting.pojo.MeetingParticipants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: xiaofine
 */
@Service
public class MeetingService {

    @Autowired
    private MeetingDao meetingDao;

    @Autowired
    private MeetingParticipantsDao meetingParticipantsDao;

    public void save(Meeting meeting){
        meetingDao.save(meeting);
    }

    public void update(Meeting meeting){
        meetingDao.update(meeting);
    }

    public void delete(Integer id){
        meetingDao.delete(id);
    }

    public List<Meeting> findAll(){
        return meetingDao.findAll();
    }

    public Meeting findById(Integer id){
        return meetingDao.findById(id);
    }

    public void addMeeting(Meeting meeting, Integer[] mps) {
        meeting.setReservationtime(new Date());
        meetingDao.save(meeting);

        for (int i = 0; i < mps.length; i++) {
            MeetingParticipants meetingParticipants = new MeetingParticipants();
            meetingParticipants.setEmployeeid(mps[i]);
            meetingParticipants.setMeetingid(meeting.getMeetingid());
            meetingParticipantsDao.save(meetingParticipants);
        }
    }
}
