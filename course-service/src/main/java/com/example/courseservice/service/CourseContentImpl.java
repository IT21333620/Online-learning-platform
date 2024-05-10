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
import java.util.Optional;

@Service
public class CourseContentImpl implements CourseContentService{

    //Injecting the CourseContentRepo and MediaRepo
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

        //Checking if the course exists
        if (courseContentRepo.findByCode(ID) == null) {
            throw new CourseCollectionException("Course with \" + id + \" not found!");
        }

        //Setting the media to the course content
        media = mediaRepo.save(media);
        courseContent.setMedia(media);
        courseContent.setCourseId(ID);
        courseContent.setCreatedAt(new Date(System.currentTimeMillis()));
        courseContentRepo.save(courseContent);
    }

    @Override
    public void updateCourseContent(String ID,CourseContent courseContent) throws CourseCollectionException {

        //Checking if the course exists
        Optional<CourseContent> courseContent1 = courseContentRepo.findById(ID);
        if (courseContent1 == null) {
            throw new CourseCollectionException(CourseCollectionException.CourseNotFoundException(ID));
        }

        //Updating the course content
        CourseContent courseContentToUpdate = courseContent1.get();
        courseContentToUpdate.setTitle(courseContent.getTitle());
        courseContentToUpdate.setDescription(courseContent.getDescription());
        courseContentToUpdate.setUpdatedAt(new Date(System.currentTimeMillis()));
        courseContentRepo.save(courseContentToUpdate);
    }

    @Override
    public void deleteCourseContent(String ID) throws CourseCollectionException {

        //Checking if the course exists
        Optional<CourseContent> courseContent = courseContentRepo.findById(ID);
        if (courseContent == null) {
            throw new CourseCollectionException(CourseCollectionException.CourseNotFoundException(ID));
        }

        courseContentRepo.deleteById(ID);
    }
}
