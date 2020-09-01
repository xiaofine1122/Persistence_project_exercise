package com.xiaofine.meeting.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaofine.meeting.model.Meeting;
import org.apache.ibatis.annotations.Param;

/**
 * @author: xiaofine
 */
public interface MeetingMapper extends BaseMapper<Meeting> {
    Integer addMeeting(Meeting meeting);

    void addParticipants(@Param("meetingid") Integer meetingid, @Param("mps") Integer[] mps);
}
