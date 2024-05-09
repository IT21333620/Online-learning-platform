package com.example.courseservice.service;

import com.example.courseservice.exception.CourseCollectionException;
import com.example.courseservice.model.Course;
import com.example.courseservice.model.CourseContent;
import com.example.courseservice.model.Media;
import com.example.courseservice.repo.CourseContentRepo;
import com.example.courseservice.repo.MediaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CourseContentImpl implements CourseContentService{

    @Autowired
    private CourseContentRepo courseContentRepo;
    @Autowired
    private MediaRepo mediaRepo;


    @Override
    public List<CourseContent> getContent(String code) throws CourseCollectionException {
        List<CourseContent> courseList = courseContentRepo.findByCode(code);
        if (!courseList.isEmpty()){
            return courseList;
        } else {
            throw new CourseCollectionException(CourseCollectionException.CourseContentNotFoundException(code));
        }

    }

    @Override
    public void addCourseContent(String ID,CourseContent courseContent, Media media) throws CourseCollectionException {
        if (courseContentRepo.findByCode(ID) == null) {
            throw new CourseCollectionException("Course with \" + id + \" not found!");
        }
        media = mediaRepo.save(media);
        courseContent.setMedia(media);
        courseContent.setCourseId(ID);
        courseContent.setCreatedAt(new Date(System.currentTimeMillis()));
        courseContentRepo.save(courseContent);
    }
}
