package com.xiaofine.meeting.dao;

import com.xiaofine.meeting.dao.base.BaseDaoImpl;
import com.xiaofine.meeting.pojo.MeetingRoom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: xiaofine
 */
@Repository("meetingRoomDao")
@Transactional
public class MeetingRoomDaoImpl extends BaseDaoImpl<MeetingRoom> implements MeetingRoomDao{
    public Class getEntityClass() {
        return MeetingRoom.class;
    }
}
