package com.example.courseservice.service;

import com.example.courseservice.exception.CourseCollectionException;
import com.example.courseservice.model.CourseContent;
import com.example.courseservice.model.Media;

import java.util.List;

public interface CourseContentService {

    public List<CourseContent> getContent(String code)throws CourseCollectionException;

    public void addCourseContent(String ID, CourseContent courseContent, Media media) throws CourseCollectionException;
}
