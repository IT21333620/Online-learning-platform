package com.example.courseservice.repo;

import com.example.courseservice.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepo extends MongoRepository<Course, String> {

    @Query("{courseId: ?0}")
    Optional<Course> findByCourseId(String courseId);

    @Query("{name: ?0}")
    Optional<Course> findByName(String name);

    @Query("{approved: true}")
    List<Course> findApprovedCourses();
}
