package com.example.demo;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @RequestMapping("/")
    public String HomePage(){
        return "index";
    }

    //students page, get list of students and load students.html
    @RequestMapping("/students")
    public String GetStudents(Model model){
        model.addAttribute("students", DemoApplication.studentService.GetAllStudents());
        return "students";
    }

    //student create form
    @RequestMapping("/students/createform")
    public String StudentCreationForm(){
        return "studentcreateform";
    }

    //class enrollment page
    @RequestMapping("/students/enrollment/{studentId}")
    public String EnrollmentPage(Model model, @PathVariable int studentId){
        model.addAttribute("courses", DemoApplication.courseService.GetAllCourses());
        model.addAttribute("student", DemoApplication.studentService.FindStudentByID(studentId));
        model.addAttribute("courseService", DemoApplication.courseService);
        return "enrollment";
    }

    //courses page
    @RequestMapping("/courses")
    public String CoursesPage(Model model){
        model.addAttribute("courses", DemoApplication.courseService.GetAllCourses());
        return "courses";
    }

    //online course creation form
    @RequestMapping("/courses/onlinecreateform")
    public String OnlineCourseCreationForm(){
        return "onlinecreateform";
    }

    //classroom course creation form
    @RequestMapping("/courses/classroomcreateform")
    public String ClassRoomCourseCreationForm(){
        return "classroomcreateform";
    }

    //course participants page
    @RequestMapping("/courses/courseparticipants/{id}")
    public String GetParticipants(Model model, @PathVariable int id){
        model.addAttribute("participants", DemoApplication.courseService.GetAllParticipants(id));
        model.addAttribute("course", DemoApplication.courseService.FindCourseByID(id));
        return "courseparticipants";
    }

    //edit course page
    @RequestMapping("/courses/edit/{id}")
    public String EditCourse(Model model, @PathVariable int id){
        model.addAttribute("course", DemoApplication.courseService.FindCourseByID(id));
        return "editcourse";
    }
}
