package com.example.demo;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public interface StudentService {
    public void CreateStudent(String firstName, String lastName) throws IOException;
    public void DeleteStudent(int id) throws IOException;
    public List<Student> GetAllStudents();
    public void LoadStudents() throws IOException, ClassNotFoundException;
    public int CreateStudentID();
    public Student FindStudentByID(int id);
}

class StudentServiceClass implements StudentService{
    List<Student> students;

    public StudentServiceClass(){
        students = new ArrayList<Student>();
    }

    //Creates student
    public void CreateStudent(String firstName, String lastName) throws IOException{
        students.add(new Student(CreateStudentID(), firstName, lastName));
        System.out.println("Added student");
        students.forEach(student -> {
            System.out.println(student.GetFirstName());
        });
        DemoApplication.fileService.SaveStudentData();
    }

    //Creates unique Student ID
    public int CreateStudentID(){
        //Sort students by ID
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
            return Integer.valueOf(s1.GetId()).compareTo(s2.GetId());
        }});

        //Create new ID by comparing list until there's an unused ID
        int id = 1;
        for (int i = 0; i < students.size(); i++){
            if (id == students.get(i).GetId()) id += 1; 
            else return id;
        }
        return id;
    }
    
    //Deletes student
    public void DeleteStudent(int id) throws IOException {
        List<Course> courses = DemoApplication.courseService.GetAllCourses();
        Student badStudent = FindStudentByID(id);
        if(students.contains(badStudent)){
            for (Course c : courses){
                List<Student> participants = c.GetParticipants();
                if(participants.contains(badStudent)) c.RemoveFromParticipants(badStudent);
            }
            students.remove(badStudent);
        }else {
            System.out.println("Student with this ID doesn't exist.");
        }
        DemoApplication.fileService.SaveStudentData();
    }
    
    //Returns list of students
    public List<Student> GetAllStudents() {
        return students;
    }

    //Loads students from file
    public void LoadStudents() throws IOException, ClassNotFoundException {
        if (this.students == null) {System.out.println("Students is null"); return;}
        List<Student> loadedStudents = DemoApplication.fileService.LoadStudentData();
        if (loadedStudents == null) {System.out.println("No student data to load"); return;}
        else{
            this.students = loadedStudents;
        }
        
    }
    
    //Returns Student object based on ID if it exists
    public Student FindStudentByID(int id) {
        for(Student s : students) {
            if (Integer.valueOf(id).equals(s.GetId()))
            { return s;}
        }
        return null;
    }
}