package com.olp.paymentservice.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.olp.paymentservice.dto.StripeChargeDto;
import com.olp.paymentservice.model.Payment;
import com.olp.paymentservice.repository.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StripeService {

    private final PaymentRepository paymentRepository;

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @PostConstruct
    public void init(){
        Stripe.apiKey = stripeApiKey;
    }

    public StripeChargeDto charge(StripeChargeDto chargeRequest) {

        try {
            chargeRequest.setSuccess(false);
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", (int) (chargeRequest.getAmount() * 100));
            chargeParams.put("currency", "USD");
            chargeParams.put("description", "Payment for id " + chargeRequest.getAdditionalInfo().getOrDefault("ID_TAG", ""));
            chargeParams.put("source", chargeRequest.getStripeToken());
            Map<String, Object> metaData = new HashMap<>();
            metaData.put("id", chargeRequest.getChargeId());
            metaData.putAll(chargeRequest.getAdditionalInfo());
            chargeParams.put("metadata", metaData);
            Charge charge = Charge.create(chargeParams);
            chargeRequest.setMessage(charge.getOutcome().getSellerMessage());

            if (charge.getPaid()) {
                chargeRequest.setChargeId(charge.getId());
                chargeRequest.setSuccess(true);

                // Save payment details to MongoDB
                Payment payment = new Payment();
                payment.setStripeChargeId(charge.getId());
                payment.setUserId(chargeRequest.getUserId());
                payment.setCourseId(chargeRequest.getCourseId());
                payment.setAmount(chargeRequest.getAmount());
                payment.setDescription("Payment for id " + chargeRequest.getAdditionalInfo().getOrDefault("ID_TAG", ""));
                payment.setMetaData(chargeRequest.getAdditionalInfo());
                paymentRepository.save(payment);
            }
            
            return chargeRequest;
        } catch (StripeException e) {
            log.error("StripeService (charge)", e);
            throw new RuntimeException(e.getMessage());
        }
    }

}
