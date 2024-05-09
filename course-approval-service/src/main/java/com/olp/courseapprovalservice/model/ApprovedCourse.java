package com.olp.courseapprovalservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "approved-courses")
public class ApprovedCourse {

    @Id
    private String id;
    private Object course;
    private boolean approved;

}
