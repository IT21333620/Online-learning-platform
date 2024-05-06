package com.ds.userenrolmentservice.service;

import com.ds.userenrolmentservice.DTO.EnrolmentDTO;
import com.ds.userenrolmentservice.exception.EnrolmentCollectionException;
import com.ds.userenrolmentservice.model.Enrolment;
import com.ds.userenrolmentservice.repo.EnrolmentRepo;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EnrolmentServiceImpl implements EnrolmentService {

    @Autowired
    private EnrolmentRepo enrolmentRepo;


    @Override
    public EnrolmentDTO createEnrolment(EnrolmentDTO enrolmentDTO) throws ConstraintViolationException, EnrolmentCollectionException {
        Optional<Enrolment> enrolmentOptional = enrolmentRepo.findByUserIdAndCourseId(enrolmentDTO.getUserId(), enrolmentDTO.getCourseId());
        if(enrolmentOptional.isPresent()){
            throw new EnrolmentCollectionException(EnrolmentCollectionException.AlreadyExists());
        }else {
            Enrolment enrolment = new Enrolment();
            enrolment.setUserId(enrolmentDTO.getUserId());
            enrolment.setCourseId(enrolmentDTO.getCourseId());
            enrolment.setCreatedAt(new Date(System.currentTimeMillis()));
            enrolmentRepo.save(enrolment);
        }
        return enrolmentDTO;
    }

    @Override
    public List<EnrolmentDTO> getAllEnrolments() {
        return List.of();
    }

    @Override
    public EnrolmentDTO getSingleEnrolmentById(String id) throws EnrolmentCollectionException {
        return null;
    }

    @Override
    public List<EnrolmentDTO> getEnrolmentByUserId(String userId) throws EnrolmentCollectionException {
        return List.of();
    }

    @Override
    public List<EnrolmentDTO> getEnrolmentByCourseId(String courseId) throws EnrolmentCollectionException {
        return List.of();
    }

    @Override
    public void deleteEnrolmentById(String id) throws EnrolmentCollectionException {

    }
}
