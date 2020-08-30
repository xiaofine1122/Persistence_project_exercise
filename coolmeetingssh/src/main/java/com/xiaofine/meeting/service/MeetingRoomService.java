package com.xiaofine.meeting.service;

import com.xiaofine.meeting.dao.MeetingRoomDao;
import com.xiaofine.meeting.pojo.MeetingRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: xiaofine
 */
@Service
public class MeetingRoomService {

    @Autowired
    private MeetingRoomDao meetingRoomDao;

    public void save(MeetingRoom meetingRoom){
        meetingRoomDao.save(meetingRoom);
    }

    public void update(MeetingRoom meetingRoom){
        meetingRoomDao.update(meetingRoom);
    }

    public void delete(Integer id){
        meetingRoomDao.delete(id);
    }

    public List<MeetingRoom> findAll(){
        return meetingRoomDao.findAll();
    }

    public MeetingRoom findById(Integer id){
        return meetingRoomDao.findById(id);
    }
}
