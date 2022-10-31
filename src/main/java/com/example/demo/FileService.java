package com.example.demo;

import java.io.*;
import java.util.List;

public class FileService {
    
    //Save current students object into file
    public void SaveStudentData() throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream("studentInfo.dat");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(DemoApplication.studentService.GetAllStudents());
        objectOutputStream.flush();
        objectOutputStream.close();
        System.out.println("Student data saved");
    }

    //Load students object from file
    public List<Student> LoadStudentData() throws IOException, ClassNotFoundException{
        if(!new File("studentInfo.dat").exists()) return null;
        FileInputStream fileInputStream = new FileInputStream("studentInfo.dat");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        List<Student> loadedStudents = (List<Student>) objectInputStream.readObject();
        objectInputStream.close();
        System.out.println("Student data loaded");
        return loadedStudents;
    }

    //Save current courses object into file
    public void SaveCourseData() throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream("courseInfo.dat");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(DemoApplication.courseService.GetAllCourses());
        objectOutputStream.flush();
        objectOutputStream.close();
        System.out.println("Course data saved");
    }

    //Load courses object from file
    public List<Course> LoadCourseData() throws IOException, ClassNotFoundException{
        if(!new File("courseInfo.dat").exists()) return null;
        FileInputStream fileInputStream = new FileInputStream("courseInfo.dat");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        List<Course> loadedCourses = (List<Course>) objectInputStream.readObject();
        objectInputStream.close();
        System.out.println("Course data loaded");
        return loadedCourses;
    }

}
