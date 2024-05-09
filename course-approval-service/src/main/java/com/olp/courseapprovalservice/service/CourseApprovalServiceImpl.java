package com.olp.courseapprovalservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olp.courseapprovalservice.model.ApprovedCourse;
import com.olp.courseapprovalservice.repository.ApprovedCourseRepository;

@Service
public class CourseApprovalServiceImpl {

    @Autowired
    private ApprovedCourseRepository approvedCourseRepository;

    // @Override
    // public List<ApprovedCourse> getPendingCourses() {
        
    // }

    // @Override
    // public ApprovedCourse approveCourse(String courseId) {
    //     Course course = courseRepository.findById(courseId).orElse(null);
    //     if (course != null) {
    //         ApprovedCourse approvedCourse = new ApprovedCourse();
    //         approvedCourse.setCourse(course);
    //         approvedCourse.setApproved(true);
    //         approvedCourseRepository.save(approvedCourse);
    //         return approvedCourse;
    //     } else {
    //         return null;
    //     }
    // }

}
