package com.xiaofine.meeting.mapper;

import com.xiaofine.meeting.model.MeetingRoom;

import java.util.List;

/**
 * @author: xiaofine
 */
public interface MeetingRoomMapper {
    List<MeetingRoom> getAllMeetRoom();

    MeetingRoom getMeetRoomById(Integer roomid);

    Integer updateRoom(MeetingRoom meetingRoom);

    Integer doAddMr(MeetingRoom meetingRoom);
}
