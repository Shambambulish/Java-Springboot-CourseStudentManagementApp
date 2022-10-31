package com.example.demo;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class MyRestController {
    
    //Create student and redirect back to list of students
    @PostMapping("students/studentcreateform/create")
    public RedirectView CreateStudent(String firstName, String lastName) throws IOException{
        DemoApplication.studentService.CreateStudent(firstName, lastName);
        return new RedirectView("/students");
    }
    
    //Delete student and redirect back to list of students
    @GetMapping("students/delete/{id}")
    public RedirectView DeleteStudent(@PathVariable int id) throws IOException{
        DemoApplication.studentService.DeleteStudent(id);
        return new RedirectView("/students");
    }

    //Create online course and redirect back to list of courses
    @PostMapping("courses/onlinecreateform/create")
    public RedirectView CreateOnlineCourse(String name, String teacher, int maxParticipants, int credit, String startDate, String endDate, String description, String remoteSessionLink){
        DemoApplication.courseService.CreateOnlineCourse(name, teacher, maxParticipants, credit, startDate, endDate, description, remoteSessionLink);
        return new RedirectView("/courses");
    }

    //Create classroom course and redirect back to list of courses
    @PostMapping("courses/classroomcreateform/create")
    public RedirectView CreateClassRoomCourse(String name, String teacher, int maxParticipants, int credit, String startDate, String endDate, String description, String classRoomID){
        DemoApplication.courseService.CreateClassRoomCourse(name, teacher, maxParticipants, credit, startDate, endDate, description, classRoomID);
        return new RedirectView("/courses");
    }

    //Delete course and redirect back to list of courses
    @GetMapping("courses/delete/{id}")
    public RedirectView DeleteCourse(@PathVariable int id) throws IOException{
        DemoApplication.courseService.DeleteCourse(id);
        return new RedirectView("/courses");
    }

    //Enroll a student to course and redirect to that course's participant list to show the result
    @GetMapping("students/enroll/{studentId}/{courseId}")
    public RedirectView EnrollToCourse(@PathVariable int studentId, @PathVariable int courseId) throws IOException{
        DemoApplication.courseService.AddStudentToCourse(studentId, courseId);
        return new RedirectView("/courses/courseparticipants/"+ courseId);
    }

    //Kick a student from a course and redirect to that course's participant list to show the result
    @GetMapping("students/kickfromcourse/{studentId}/{courseId}")
    public RedirectView KickFromCourse(@PathVariable int studentId, @PathVariable int courseId) throws IOException{
        DemoApplication.courseService.RemoveStudentFromCourse(studentId, courseId);
        return new RedirectView("/courses/courseparticipants/"+ courseId);
    }

    //Edit a course and redirect back to courses page
    @PostMapping("/courses/edit/submit")
    public RedirectView EditCourse(int courseId, String name, String teacher, int credit, int maxParticipants, String startDate, String endDate, String description, String classRoomID, String remoteSessionLink){
        DemoApplication.courseService.EditCourse(courseId, name, teacher, credit, maxParticipants, startDate, endDate, description, classRoomID, remoteSessionLink);
        return new RedirectView("/courses");
    }
}
