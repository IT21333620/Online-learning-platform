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
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;
    @Autowired
    private ImageUploadService imageService;

    @GetMapping("get-course-content/{courseID}")
    private ResponseEntity<?> getCourseContent(@PathVariable("courseID") String code){
        try{
            return ResponseHandler.responseBuilder("Course Conetent found", HttpStatus.OK, courseContentService.getContent(code));
        } catch (Exception e){
            return ResponseHandler.responseBuilder(e.getMessage(),HttpStatus.BAD_REQUEST,null);
        }
    }

    @PostMapping("/add-course-content/{courseID}")
    private ResponseEntity<?> addCourseContent(@PathVariable("courseID") String ID, @RequestPart("courseContent") CourseContent courseContent, @RequestPart("file") MultipartFile file){
        try{
            String url = imageService.upload(file);
            Media media = new Media();
            media.setUrl(url);
            media.setCreatedAt(new Date(System.currentTimeMillis()));
            courseContent.setMedia(media);
            courseContentService.addCourseContent(ID, courseContent);
            return ResponseHandler.responseBuilder("Course content added successfully", HttpStatus.OK, null);
        } catch (CourseCollectionException e){
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @PostMapping
    public String upload(@RequestParam("file") MultipartFile multipartFile) {
        return imageService.upload(multipartFile);
    }
}
