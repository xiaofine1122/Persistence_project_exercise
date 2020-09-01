package com.xiaofine.meeting.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaofine.meeting.mapper.MeetingParticipantsMapper;
import com.xiaofine.meeting.model.MeetingParticipants;
import com.xiaofine.meeting.service.MeetingParticipantsService;
import org.springframework.stereotype.Service;

/**
 * @author: xiaofine
 */
@Service
public class MeetingParticipantsServiceImpl extends ServiceImpl<MeetingParticipantsMapper, MeetingParticipants> implements MeetingParticipantsService {
}
