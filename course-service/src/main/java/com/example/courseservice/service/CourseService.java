package com.example.courseservice.service;

import com.example.courseservice.exception.CourseCollectionException;
import com.example.courseservice.model.Course;

import java.util.List;

public interface CourseService {

    public List<Course> getAllCourses();

    public void CreateCourse(Course course) throws CourseCollectionException;

    public Course getCourseByCode(String id) throws CourseCollectionException;

    public List<Course> getApprovedCourses();
}
