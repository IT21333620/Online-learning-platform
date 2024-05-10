package com.olp.paymentservice.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class StripeChargeDto {

    private String stripeToken;
	private String userId;
	private String courseId;
	private Double amount;
	private Boolean success;
	private String message;
	private String chargeId;
	private Map<String,Object> additionalInfo = new HashMap<>();
}
