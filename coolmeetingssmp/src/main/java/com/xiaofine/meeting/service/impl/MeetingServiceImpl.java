package com.xiaofine.meeting.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaofine.meeting.mapper.MeetingMapper;
import com.xiaofine.meeting.model.Meeting;
import com.xiaofine.meeting.service.MeetingService;
import org.springframework.stereotype.Service;

/**
 * @author: xiaofine
 */
@Service
public class MeetingServiceImpl extends ServiceImpl<MeetingMapper, Meeting> implements MeetingService {

}
