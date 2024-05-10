package com.olp.paymentservice.model;

import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value = "payments")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Payment {

    @Id
    private String id;
    private String stripeChargeId;
    private String userId;
    private String courseId;
    private Double amount;
    private String description;
    private Map<String, Object> metaData;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

}
