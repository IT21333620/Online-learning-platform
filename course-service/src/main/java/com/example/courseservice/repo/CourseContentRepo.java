package com.example.courseservice.repo;

import com.example.courseservice.model.CourseContent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseContentRepo extends MongoRepository<CourseContent, String>{

}
