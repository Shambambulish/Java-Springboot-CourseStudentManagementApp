package com.example.demo;

import java.util.List;

public class ClassRoomCourse extends Course{
    private String classRoomID;

    public ClassRoomCourse() {
    }

    public ClassRoomCourse(int id, String name, String teacher, int maxParticipants, int credit, String startDate, String endDate, String description, String classRoomID) {
        super(id, name, teacher, maxParticipants, credit, startDate, endDate, description);
        this.classRoomID = classRoomID;
    }

    public void EditCourse(int id, String name, String teacher, int maxParticipants, int credit, String startDate, String endDate, String description, String classRoomID, List<Student> participants){
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.maxParticipants = maxParticipants;
        this.credit = credit;
        this.startDate = startDate;
        this.endDate = endDate;
        this.classRoomID = classRoomID;
        this.description = description;
        this.participants = participants;
    }

    public String GetClassRoomID() {return classRoomID;}
    public String GetRemoteSessionLink() {return "n/a";}
}
