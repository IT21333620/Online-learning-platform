package com.example.courseservice.controller;

import com.example.courseservice.exception.CourseCollectionException;
import com.example.courseservice.model.CourseContent;
import com.example.courseservice.model.Media;
import com.example.courseservice.response.ResponseHandler;
import com.example.courseservice.service.CourseContentService;
import com.example.courseservice.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/content")
@CrossOrigin("*")
public class CourseContentController {

    // Autowired services for course content and image upload
    @Autowired
    private CourseContentService courseContentService;
    @Autowired
    private ImageUploadService imageService;

    //Retrieve course content by course ID
    @GetMapping("get-course-content/{courseID}")
    private ResponseEntity<?> getCourseContent(@PathVariable("courseID") String code){
        try{
            return ResponseHandler.responseBuilder("Course Conetent found", HttpStatus.OK, courseContentService.getContent(code));
        } catch (Exception e){
            return ResponseHandler.responseBuilder(e.getMessage(),HttpStatus.BAD_REQUEST,null);
        }
    }

    //POST requests to add course content
    @PostMapping("/add-course-content/{courseID}")
    private ResponseEntity<?> addCourseContent(@PathVariable("courseID") String ID, @RequestPart("courseContent") CourseContent courseContent, @RequestPart("file") MultipartFile file){
        try{
            // Upload image to cloud storage
            String url = imageService.upload(file);

            // Create media object and set URL and creation date
            Media media = new Media();
            media.setUrl(url);
            media.setCreatedAt(new Date(System.currentTimeMillis()));
            courseContentService.addCourseContent(ID, courseContent, media);
            return ResponseHandler.responseBuilder("Course content added successfully", HttpStatus.CREATED, null);
        } catch (CourseCollectionException e){
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @GetMapping("/get-course-content-By-ID/{contentID}")
    private ResponseEntity<?> getCourseContentByID(@PathVariable("contentID") String code){
        try{
            return ResponseHandler.responseBuilder("Course content found", HttpStatus.OK, courseContentService.getContentByID(code));
        } catch (CourseCollectionException e){
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }


    @PostMapping
    public String upload(@RequestParam("file") MultipartFile multipartFile) {
        return imageService.upload(multipartFile);
    }

    // PUT requests to update course content
    @PutMapping("/update-course-content/{courseID}")
    public ResponseEntity<?> updateCourseContent(@PathVariable("courseID") String code, @RequestBody CourseContent courseContent){
        try{
            courseContentService.updateCourseContent(code,courseContent);
            return ResponseHandler.responseBuilder("Course content updated successfully", HttpStatus.OK, null);
        } catch (CourseCollectionException e){
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    // DELETE requests to delete course content
    @DeleteMapping("/delete-course-content/{courseID}")
    public ResponseEntity<?> deleteCourseContent(@PathVariable("courseID") String code){
        try{
            courseContentService.deleteCourseContent(code);
            return ResponseHandler.responseBuilder("Course content deleted successfully", HttpStatus.OK, null);
        } catch (CourseCollectionException e){
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }
}
