package com.example.courseservice.service;

import com.example.courseservice.exception.CourseCollectionException;
import com.example.courseservice.model.Course;
import com.example.courseservice.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CourseImpl implements CourseService{

    @Autowired
    private CourseRepo courseRepo;

    @Override
    public List<Course> getAllCourses() {
        List<Course> courseList = courseRepo.findAll();
        if(!courseList.isEmpty()){
            return courseList;
        } else {
            return new ArrayList<Course>();
        }
    }

    @Override
    public void CreateCourse(Course course,String Url) throws CourseCollectionException {

        //Checking if the course code or name already exists
        Optional<Course> courseOptional = courseRepo.findByCourseId(course.getCourseId());
        Optional<Course> courseOptional1 = courseRepo.findByName(course.getName());

        //Throwing an exception if the course code or name already exists
        if(courseOptional.isPresent()){
            throw new CourseCollectionException(CourseCollectionException.CourseCodeAlreadyAllocated(course.getId()));
        } else if (courseOptional1.isPresent()){
            throw new CourseCollectionException(CourseCollectionException.CourseNameAlreadyExists(course.getName()));
        } else {

            //Setting the course URL and saving the course
            course.setUrl(Url);
            course.setApproved(false);
            course.setCreatedAt(new Date(System.currentTimeMillis()));
            courseRepo.save(course);
        }
    }

    @Override
    public Course getCourseByCode(String id) throws CourseCollectionException {
        //Checking if the course exists
        Optional<Course> courseOptional = courseRepo.findByCourseId(id);
        if(courseOptional.isEmpty()){
            throw new CourseCollectionException(CourseCollectionException.CourseNotFoundException(id));
        } else {
            return courseOptional.get();
        }
    }

    @Override
    public List<Course> getApprovedCourses() {
        List<Course> courseList = courseRepo.findApprovedCourses();
        if(!courseList.isEmpty()){
            return courseList;
        } else {
            return new ArrayList<Course>();
        }
    }

    @Override
    public List<Course> getUnApprovedCourses() {
        List<Course> courseList = courseRepo.findUnapprovedCourses();
        if(!courseList.isEmpty()){
            return courseList;
        } else {
            return new ArrayList<Course>();
        }
    }


}
