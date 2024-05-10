package com.ds.monitoringservice.model;

import com.example.courseservice.model.Course;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class LearnerProgress {
//    private Object enrolment;

    @JsonProperty("data")
    private List<LearnerProgress> learnerProgressList;

    private String id;
    private String userId;
    private String courseId;
    private Course course; // Assuming you have a Course class for the nested object
    private String createdAt;
    private String updatedAt;
    private String status;
}
