package com.example.courseservice.repo;

import com.example.courseservice.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepo extends MongoRepository<Course, String> {

    //Find any course by courseId
    @Query("{courseId: ?0}")
    Optional<Course> findByCourseId(String courseId);

    //Find any course by courseName
    @Query("{name: ?0}")
    Optional<Course> findByName(String name);

    //Find approved course by courseCode
    @Query("{approved: true}")
    List<Course> findApprovedCourses();

    //Find unapproved course by courseCode
    @Query("{approved: false}")
    List<Course> findUnapprovedCourses();
}
