package com.example.courseservice.controller;

import com.example.courseservice.model.Course;
import com.example.courseservice.response.ResponseHandler;
import com.example.courseservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/get-all-courses")
    public ResponseEntity<?> getAllCourses(){
        List<Course> courseList = courseService.getAllCourses();
        if(!courseList.isEmpty()){
            return ResponseHandler.responseBuilder("Get all courses",  HttpStatus.OK ,courseList);
        } else {
            return ResponseHandler.responseBuilder("No courses available", HttpStatus.NOT_FOUND, null);
        }
    }

    @PostMapping("/add-course")
    public ResponseEntity<?> addCourse(@RequestBody Course course){
        try{
            courseService.CreateCourse(course);
            return ResponseHandler.responseBuilder("Course added successfully", HttpStatus.CREATED, null);
        } catch (Exception e){
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @GetMapping("/get-course-by-code/{code}")
    public ResponseEntity<?> getCourseByCode(@PathVariable("code") String code){
        try {
            return ResponseHandler.responseBuilder("Course found", HttpStatus.OK, courseService.getCourseByCode(code));
        } catch (Exception e){
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    @GetMapping("/get-approved-courses")
    public ResponseEntity<?> getApprovedCourses(){
        List<Course> courseList = courseService.getApprovedCourses();
        if(!courseList.isEmpty()){
            return ResponseHandler.responseBuilder("Get all approved courses",  HttpStatus.OK ,courseList);
        } else {
            return ResponseHandler.responseBuilder("No approved courses available", HttpStatus.NOT_FOUND, null);
        }
    }




}
