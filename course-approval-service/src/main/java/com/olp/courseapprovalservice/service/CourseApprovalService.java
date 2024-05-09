package com.olp.courseapprovalservice.service;

import java.util.List;

import com.olp.courseapprovalservice.model.ApprovedCourse;

public interface CourseApprovalService {
    List<ApprovedCourse> getPendingCourses();
    ApprovedCourse approveCourse(String courseId);
}
