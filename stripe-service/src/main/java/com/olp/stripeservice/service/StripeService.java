package com.olp.stripeservice.service;

import com.olp.stripeservice.dto.EmailRequestDto;
import com.olp.stripeservice.dto.StripeChargeDto;
import com.olp.stripeservice.model.Payment;
import com.olp.stripeservice.repository.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class StripeService {

    private final PaymentRepository paymentRepository;
    private final WebClient webClient;

    @Value("${notification.service.url}")
    private String notificationServiceUrl;

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

                // Call notification service
                EmailRequestDto emailRequestDTO = new EmailRequestDto();
                emailRequestDTO.setToEmail("designbyhasitha@gmail.com"); // Make this dynamic
                emailRequestDTO.setSubject("Payment Confirmation");
                emailRequestDTO.setBody("Your payment was successful.");

                sendNotification(emailRequestDTO);

            }

            return chargeRequest;
        } catch (StripeException e) {
            log.error("StripeService (charge)", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    private void sendNotification(EmailRequestDto emailRequestDto) {

        webClient.post()
                .uri(notificationServiceUrl + "/api/send-email")
                .body(Mono.just(emailRequestDto), EmailRequestDto.class)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe();
    }

}
