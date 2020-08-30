package com.xiaofine.meeting.dao;

import com.xiaofine.meeting.dao.base.BaseDaoImpl;
import com.xiaofine.meeting.pojo.MeetingParticipants;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: xiaofine
 */
@Repository("meetingParticipantsDao")
@Transactional
public class MeetingParticipantsDaoImpl extends BaseDaoImpl<MeetingParticipants> implements MeetingParticipantsDao {
    public Class getEntityClass() {
        return MeetingParticipants.class;
    }
}
