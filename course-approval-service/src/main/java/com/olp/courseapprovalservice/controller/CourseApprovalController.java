package com.olp.courseapprovalservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.olp.courseapprovalservice.model.ApprovedCourse;
import com.olp.courseapprovalservice.service.CourseApprovalService;

public class CourseApprovalController {

    @Autowired
    private CourseApprovalService courseApprovalService;

    @GetMapping("/courses/pending")
    public List<ApprovedCourse> getPendingCourses() {
        return courseApprovalService.getPendingCourses();
    }

    @PostMapping("/courses/{courseId}/approve")
    public ApprovedCourse approveCourse(@PathVariable String courseId) {
        return courseApprovalService.approveCourse(courseId);
    }

}
