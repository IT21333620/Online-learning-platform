package com.ds.monitoringservice.controller;

import com.ds.monitoringservice.ResponseHandler;
import com.ds.monitoringservice.model.LearnerProgress;
import com.ds.monitoringservice.model.LearnerProgressResponse;
import com.ds.monitoringservice.repo.LearnerProgressRepo;
import com.ds.monitoringservice.service.LearnerProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/learnerProgress")
public class LearnerProgressController {
    @Autowired
    private LearnerProgressService learnerProgressService;

    @Autowired
    private LearnerProgressRepo learnerProgressRepo;

    @GetMapping("/getAllProgress")
    public ResponseEntity<?> getAllProgress() {
        LearnerProgressResponse learnerProgress = learnerProgressService.getAllProgress();
        if (learnerProgress != null && !learnerProgress.getLearnerProgressList().isEmpty()) {
            return ResponseHandler.responseBuilder("Progress retrieved successfully", HttpStatus.OK, learnerProgress);
        } else {
            return ResponseHandler.responseBuilder("No progress found", HttpStatus.NOT_FOUND, null);
        }
    }

    @GetMapping("/getCourseIdCounts")
    public ResponseEntity<?> getCourseIdCounts() {
        Map<String, Map<String, Integer>> courseIdCounts = learnerProgressService.getCourseIdCounts();
        if (!courseIdCounts.isEmpty()) {
            return ResponseHandler.responseBuilder("CourseId counts retrieved successfully", HttpStatus.OK, courseIdCounts);
        } else {
            return ResponseHandler.responseBuilder("No courseId counts found", HttpStatus.NOT_FOUND, null);
        }
    }

    @GetMapping("/getUserIdCounts")
    public ResponseEntity<?> getUserIdCounts() {
        Map<String, Map<String, Integer>> userIdCounts = learnerProgressService.getUserIdCounts();
        if (!userIdCounts.isEmpty()) {
            return ResponseHandler.responseBuilder("UserId counts retrieved successfully", HttpStatus.OK, userIdCounts);
        } else {
            return ResponseHandler.responseBuilder("No userId counts found", HttpStatus.NOT_FOUND, null);
        }
    }

}
