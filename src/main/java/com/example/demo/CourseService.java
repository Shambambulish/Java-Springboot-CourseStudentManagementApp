package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public interface CourseService {
    public void CreateOnlineCourse (String name, String teacher, int maxParticipants, int credit, String startDate, String endDate, String description, String remoteSessionLink);
    public void CreateClassRoomCourse (String name, String teacher, int maxParticipants, int credit, String startDate, String endDate, String description, String classRoomID);
    public int CreateCourseID();
    public void EditCourse(int courseId, String name, String teacher, int maxParticipants, int credit, String startDate, String endDate, String description, String classRoomID, String remoteSessionLink);
    public void DeleteCourse(int id);
    public Course FindCourseByID(int id);
    public List<Course> GetAllCourses();
    public void AddStudentToCourse(int studentID, int courseID);
    public void RemoveStudentFromCourse(int studentID, int courseID);
    public List<Student> GetAllParticipants(int id);
    public void LoadCourses() throws IOException, ClassNotFoundException;
}


class CourseServiceClass implements CourseService {
    List<Course> courses;
    
    public CourseServiceClass(){
        courses = new ArrayList<Course>();
    }

    //Creates OnlineCourse
    public void CreateOnlineCourse(String name, String teacher, int maxParticipants, int credit, String startDate, String endDate, String description, String remoteSessionLink) {
        courses.add(new OnlineCourse(CreateCourseID(), name, teacher, maxParticipants, credit, startDate, endDate, description, remoteSessionLink));
        System.out.println("Added course");
        //Save updated information to file
        try{
            DemoApplication.fileService.SaveCourseData();
        } catch (IOException err){
            err.printStackTrace();
        }
    }

    //Creates ClassRoomCourse
    public void CreateClassRoomCourse(String name, String teacher, int maxParticipants, int credit, String startDate, String endDate, String description, String classRoomID) {
        courses.add(new ClassRoomCourse(CreateCourseID(), name, teacher, maxParticipants, credit, startDate, endDate, description, classRoomID));
        System.out.println("Added course");
        //Save updated information to file
        try{
            DemoApplication.fileService.SaveCourseData();
        } catch (IOException err){
            err.printStackTrace();
        }
    }

    //Creates unique Course ID
    public int CreateCourseID() {
        //Sort courses by ID
        if (courses==null){return 1;}
        Collections.sort(courses, new Comparator<Course>() {
            @Override
            public int compare(Course c1, Course c2) {
            return Integer.valueOf(c1.GetId()).compareTo(c2.GetId());
        }});

        //Create new ID by comparing list until there's an unused ID
        int[] id = new int[1];
        id[0] = 1;
        for (int i = 0; i < courses.size(); i++){
            if (id[0] == courses.get(i).GetId()){id[0] = id[0] + 1;}
            else{return id[0];}
        }
        return id[0];
    }

    //Edits course
    public void EditCourse(int id, String name, String teacher, int maxParticipants, int credit, String startDate, String endDate, String description, String classRoomID, String remoteSessionLink){
        Course c = FindCourseByID(id);
        if (c instanceof OnlineCourse == true) {
            OnlineCourse oc = (OnlineCourse) c;
            oc.EditCourse(id, name, teacher, maxParticipants, credit, startDate, endDate, description, remoteSessionLink, oc.GetParticipants());
        }
        else if (c instanceof ClassRoomCourse == true) {
            ClassRoomCourse crc = (ClassRoomCourse) c;
            crc.EditCourse(id, name, teacher, maxParticipants, credit, startDate, endDate, description, classRoomID, crc.GetParticipants());
        }
        //Save updated information to file
        try{
            DemoApplication.fileService.SaveCourseData();
        } catch (IOException err){
            err.printStackTrace();
        }
    }

    //Deletes Course
    public void DeleteCourse(int id) {
        Course badCourse = FindCourseByID(id);
        if (courses.contains(badCourse)){
            courses.remove(badCourse);
        }
        else {
            System.out.println("Course with this ID doesn't exist in CourseService courses.");
        }
        //Save updated information to file
        try{
            DemoApplication.fileService.SaveCourseData();
        } catch (IOException err){
            err.printStackTrace();
        }
    }

    //Finds Course by ID
    public Course FindCourseByID(int id) {
        for(Course c : courses) {
            if (Integer.valueOf(id).equals(c.GetId()))
            { return c;}
        }
        return null;
    }

    //Returns list of courses
    public List<Course> GetAllCourses(){return courses;}

    //Adds student to course
    public void AddStudentToCourse(int studentID, int courseID){
        Student student = DemoApplication.studentService.FindStudentByID(studentID);
        Course course = FindCourseByID(courseID);
        List<Student> courseParticipants = course.GetParticipants();
        if (courseParticipants.contains(student)){System.out.println("Student already on course"); return;}
        course.AddToParticipants(student);
        //Save updated information to file
        try{
            DemoApplication.fileService.SaveCourseData();
        } catch (IOException err){
            err.printStackTrace();
        }
    }

    //Removes student from course
    public void RemoveStudentFromCourse(int studentID, int courseID){
        Student student = DemoApplication.studentService.FindStudentByID(studentID);
        Course course = FindCourseByID(courseID);
        List<Student> courseParticipants = course.GetParticipants();
        courseParticipants.forEach(x -> {System.out.println(x.GetFirstName());});
        if (!courseParticipants.contains(student)){System.out.println("Student not in course"); return;}
        course.RemoveFromParticipants(student);
        //Save updated information to file
        try{
            DemoApplication.fileService.SaveCourseData();
        } catch (IOException err){
            err.printStackTrace();
        }
    }

    //Return list of class participants
    public List<Student> GetAllParticipants(int id){
        Course course = FindCourseByID(id);
        return course.GetParticipants();
    }

    //Loads courses from file
    public void LoadCourses() throws IOException, ClassNotFoundException {
        if (this.courses == null) {System.out.println("Courses is null"); return;}
        List<Course> loadedCourses = DemoApplication.fileService.LoadCourseData();
        if (loadedCourses == null) {System.out.println("No course data to load"); return;}
        else{
            this.courses = loadedCourses;
        }
    }
}