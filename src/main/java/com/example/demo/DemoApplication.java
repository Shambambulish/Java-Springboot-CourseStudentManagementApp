package com.example.demo;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoApplication {
	public static StudentService studentService;
    public static CourseService courseService;
	public static FileService fileService;
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		SpringApplication.run(DemoApplication.class, args);

		//initialize controller objects to use in all classes
		studentService = new StudentServiceClass();
		courseService = new CourseServiceClass();
		fileService = new FileService();
		
		//load saved data
		studentService.LoadStudents();
		courseService.LoadCourses();
	}

}
