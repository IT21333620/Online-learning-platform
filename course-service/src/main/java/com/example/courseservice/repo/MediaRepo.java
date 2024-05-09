package com.example.courseservice.repo;

import com.example.courseservice.model.Media;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MediaRepo extends MongoRepository<Media, String>{

}
