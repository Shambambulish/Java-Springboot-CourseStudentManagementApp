package com.example.demo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Serializable allows saving as object
public class Course implements Serializable {
    protected int id;
    protected String name;
    protected String teacher;
    protected int maxParticipants;
    protected int credit;
    protected List<Student> participants;
    protected String startDate;
    protected String endDate;
    protected String description;


    public Course() {}

    public Course(int id, String name, String teacher, int maxParticipants, int credit, String startDate, String endDate, String description) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.maxParticipants = maxParticipants;
        this.credit = credit;
        participants = new ArrayList<Student>();
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public int GetId() {
        return this.id;
    }

    public String GetName() {
        return this.name;
    }

    public String GetTeacher() {
        return this.teacher;
    }

    public int GetMaxParticipants() {
        return this.maxParticipants;
    }

    public int GetCredit() {
        return this.credit;
    }

    public List<Student> GetParticipants() {
        return this.participants;
    }

    public String GetStartDate() {
        return this.startDate;
    }

    public String GetEndDate() {
        return this.endDate;
    }

    public String GetDescription() {
        return this.description;
    }

    //Add parameter student to course if not already there
    public void AddToParticipants(Student student) {
        if(participants.contains(student)){return;}
        participants.add(student);
    }

    //Remove parameter student from course if there
    public void RemoveFromParticipants(Student student) {
        if(participants.contains(student)){participants.remove(student);}
        else{System.out.println("Student not enrolled in course.");}
    }
}
