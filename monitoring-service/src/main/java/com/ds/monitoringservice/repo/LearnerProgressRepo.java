package com.ds.monitoringservice.repo;

import com.ds.monitoringservice.model.LearnerProgress;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LearnerProgressRepo extends MongoRepository<LearnerProgress, String> {
}
