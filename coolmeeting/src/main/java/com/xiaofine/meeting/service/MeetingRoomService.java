package com.xiaofine.meeting.service;

import com.xiaofine.meeting.mapper.MeetingRoomMapper;
import com.xiaofine.meeting.model.MeetingRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: xiaofine
 */
@Service
public class MeetingRoomService {

    @Autowired
    MeetingRoomMapper meetingRoomMapper;

    public List<MeetingRoom> getAllMeetRoom() {
        return meetingRoomMapper.getAllMeetRoom();
    }

    public MeetingRoom getMeetRoomById(Integer roomid) {
        return meetingRoomMapper.getMeetRoomById(roomid);
    }

    public Integer updateRoom(MeetingRoom meetingRoom) {
        return meetingRoomMapper.updateRoom(meetingRoom);
    }

    public Integer doAddMr(MeetingRoom meetingRoom) {
        return meetingRoomMapper.doAddMr(meetingRoom);
    }
}
