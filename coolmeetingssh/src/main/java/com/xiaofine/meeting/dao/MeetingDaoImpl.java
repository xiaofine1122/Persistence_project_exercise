package com.xiaofine.meeting.dao;

import com.xiaofine.meeting.dao.base.BaseDaoImpl;
import com.xiaofine.meeting.pojo.Meeting;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: xiaofine
 */
@Repository("meetingDao")
@Transactional
public class MeetingDaoImpl extends BaseDaoImpl<Meeting> implements MeetingDao {
    public Class getEntityClass() {
        return Meeting.class;
    }
}
