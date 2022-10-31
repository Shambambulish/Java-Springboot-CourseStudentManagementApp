package com.example.demo;

import java.util.List;

public class OnlineCourse extends Course{
    private String remoteSessionLink;

    public OnlineCourse() {
    }

    public OnlineCourse(int id, String name, String teacher, int maxParticipants, int credit, String startDate, String endDate, String description, String remoteSessionLink) {
        super(id, name, teacher, maxParticipants, credit, startDate, endDate, description);
        this.remoteSessionLink = remoteSessionLink;
    }

    public void EditCourse(int id, String name, String teacher, int maxParticipants, int credit, String startDate, String endDate, String description, String remoteSessionLink, List<Student> participants){
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.maxParticipants = maxParticipants;
        this.credit = credit;
        this.startDate = startDate;
        this.endDate = endDate;
        this.remoteSessionLink = remoteSessionLink;
        this.description = description;
        this.participants = participants;
    }

    public String GetRemoteSessionLink() {return remoteSessionLink;}
    public String GetClassRoomID() {return "n/a";}
}
