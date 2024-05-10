package com.olp.stripeservice.dto;

import lombok.Data;

@Data
public class EmailRequestDto {

    private String toEmail;
    private String subject;
    private String body;
}
