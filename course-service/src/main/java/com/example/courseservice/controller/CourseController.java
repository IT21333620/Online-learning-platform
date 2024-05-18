package com.example.courseservice.controller;

import com.example.courseservice.model.Course;
import com.example.courseservice.response.ResponseHandler;
import com.example.courseservice.service.CourseService;
import com.example.courseservice.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private ImageUploadService imageService;

    // GET request to get all courses
    @GetMapping("/get-all-courses")
    public ResponseEntity<?> getAllCourses(){
        List<Course> courseList = courseService.getAllCourses();
        if(!courseList.isEmpty()){
            return ResponseHandler.responseBuilder("Get all courses",  HttpStatus.OK ,courseList);
        } else {
            return ResponseHandler.responseBuilder("No courses available", HttpStatus.NOT_FOUND, null);
        }
    }

    // POST request to add course
    @PostMapping("/add-course")
    public ResponseEntity<?> addCourse(@RequestPart("course") Course course, @RequestPart("file") MultipartFile file){
        try{
            String url = imageService.upload(file);
            courseService.CreateCourse(course,url);
            return ResponseHandler.responseBuilder("Course added successfully", HttpStatus.CREATED, null);
        } catch (Exception e){
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    // GET request to get course by code
    @GetMapping("/get-course-by-code/{code}")
    public ResponseEntity<?> getCourseByCode(@PathVariable("code") String code){
        try {
            return ResponseHandler.responseBuilder("Course found", HttpStatus.OK, courseService.getCourseByCode(code));
        } catch (Exception e){
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    // GET request to get approved courses
    @GetMapping("/get-approved-courses")
    public ResponseEntity<?> getApprovedCourses(){
        List<Course> courseList = courseService.getApprovedCourses();
        if(!courseList.isEmpty()){
            return ResponseHandler.responseBuilder("Get all approved courses",  HttpStatus.OK ,courseList);
        } else {
            return ResponseHandler.responseBuilder("No approved courses available", HttpStatus.NOT_FOUND, null);
        }
    }

    // GET request to get unapproved courses
    @GetMapping("/get-unapproved-courses")
    public ResponseEntity<?> getUnApprovedCourses(){
        List<Course> courseList = courseService.getUnApprovedCourses();
        if(!courseList.isEmpty()){
            return ResponseHandler.responseBuilder("Get all unapproved courses",  HttpStatus.OK ,courseList);
        } else {
            return ResponseHandler.responseBuilder("No unapproved courses available", HttpStatus.NOT_FOUND, null);
        }
    }

    // Get request to get all courses by conductorId
    @GetMapping("Get-all-courses-by-conductorId/{conductorId}")
    public ResponseEntity<?> getAllCoursesByConductorId(@PathVariable("conductorId") String conductorId){
        List<Course> courseList = courseService.getAllCoursesByConductorId(conductorId);
        if(!courseList.isEmpty()){
            return ResponseHandler.responseBuilder("Get all courses by conductorId",  HttpStatus.OK ,courseList);
        } else {
            return ResponseHandler.responseBuilder("No courses available", HttpStatus.NOT_FOUND, null);
        }
    }




}
