package com.ds.userenrolmentservice.repo;

import com.ds.userenrolmentservice.model.Enrolment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EnrolmentRepo extends MongoRepository<Enrolment, String>{

    Optional<Enrolment> findByUserId(String userId);

    Optional<Enrolment> findByCourseId(String courseId);

    Optional<Enrolment> findByUserIdAndCourseId(String userId, String courseId);

    Optional<Enrolment> findById(String id);

    void deleteById(String id);
}
