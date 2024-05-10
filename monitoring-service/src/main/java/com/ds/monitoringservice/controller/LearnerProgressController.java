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

}
