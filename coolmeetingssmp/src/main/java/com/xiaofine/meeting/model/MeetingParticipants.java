package com.xiaofine.meeting.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (Meetingparticipants)实体类
 *
 * @author makejava
 * @since 2020-09-01 17:26:05
 */
@TableName("MeetingParticipants")
public class MeetingParticipants {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private Integer meetingid;

        private Integer employeeid;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getMeetingid() {
            return meetingid;
        }

        public void setMeetingid(Integer meetingid) {
            this.meetingid = meetingid;
        }

        public Integer getEmployeeid() {
            return employeeid;
        }

        public void setEmployeeid(Integer employeeid) {
            this.employeeid = employeeid;
        }

        @Override
        public String toString() {
            return "Meetingparticipants{" +
                "id=" + id +
                ", meetingid=" + meetingid +
                ", employeeid=" + employeeid +
                '}';
    }
}