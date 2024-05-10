package com.olp.emailservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailRequest {
    private String toEmail;
    private String subject;
    private String body;
}
