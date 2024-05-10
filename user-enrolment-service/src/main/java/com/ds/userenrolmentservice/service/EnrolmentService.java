package com.ds.userenrolmentservice.service;

import com.ds.userenrolmentservice.exception.EnrolmentCollectionException;
import com.ds.userenrolmentservice.model.Enrolment;
import jakarta.validation.ConstraintViolationException;

import java.util.List;

public interface EnrolmentService {

    public Enrolment createEnrolment(Enrolment enrolment)
            throws ConstraintViolationException, EnrolmentCollectionException;

    public List<Enrolment> getAllEnrolments();

    public Enrolment getSingleEnrolmentById(String id)
            throws EnrolmentCollectionException;

    public List<Enrolment> getEnrolmentByUserId(String userId)
            throws EnrolmentCollectionException;

    public List<Enrolment> getEnrolmentByCourseId(String courseId)
            throws EnrolmentCollectionException;

    public List<Enrolment> getEnrolmentByUserIdAndCourseId(String userId, String courseId)
            throws EnrolmentCollectionException;

    public void deleteEnrolmentById(String id)
            throws EnrolmentCollectionException;

    public Enrolment updateEnrolmentStatus(String userId, String courseId, Boolean status)
            throws EnrolmentCollectionException;
}
