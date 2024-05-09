package com.olp.courseapprovalservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.olp.courseapprovalservice.model.ApprovedCourse;

public interface ApprovedCourseRepository extends MongoRepository<ApprovedCourse, String>{

}
