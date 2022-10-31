package com.example.demo;

import java.io.Serializable;

//Serializable allows saving as object
public class Student implements Serializable{
    private int id;
    private String firstName;
    private String lastName;

    public Student() {}

    public Student(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int GetId() {
        return this.id;
    }
    public String GetFirstName() {
        return this.firstName;
    }
    public String GetLastName() {
        return this.lastName;
    }
}
