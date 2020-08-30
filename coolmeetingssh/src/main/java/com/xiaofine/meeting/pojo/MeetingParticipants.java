package com.xiaofine.meeting.pojo;

import javax.persistence.*;

/**
 * @author: xiaofine
 */
@Entity
@Table
public class MeetingParticipants {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
        return "MeetingParticipants{" +
                "id=" + id +
                ", meetingid=" + meetingid +
                ", employeeid=" + employeeid +
                '}';
    }
}
