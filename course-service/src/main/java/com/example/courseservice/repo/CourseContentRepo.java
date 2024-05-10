package com.example.courseservice.repo;

import com.example.courseservice.model.CourseContent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseContentRepo extends MongoRepository<CourseContent, String>{

    //Find course content by courseId
    @Query("{courseId: ?0}")
    List<CourseContent> findByCode(String courseId);

}
