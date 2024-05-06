package com.ds.userenrolmentservice.service;

import com.ds.userenrolmentservice.DTO.EnrolmentDTO;
import com.ds.userenrolmentservice.exception.EnrolmentCollectionException;
import jakarta.validation.ConstraintViolationException;

import java.util.List;

public interface EnrolmentService {

    public EnrolmentDTO createEnrolment(EnrolmentDTO enrolmentDTO)
            throws ConstraintViolationException, EnrolmentCollectionException;

    public List<EnrolmentDTO> getAllEnrolments();

    public EnrolmentDTO getSingleEnrolmentById(String id)
            throws EnrolmentCollectionException;

    public List<EnrolmentDTO> getEnrolmentByUserId(String userId)
            throws EnrolmentCollectionException;

    public List<EnrolmentDTO> getEnrolmentByCourseId(String courseId)
            throws EnrolmentCollectionException;

    public void deleteEnrolmentById(String id)
            throws EnrolmentCollectionException;
}
