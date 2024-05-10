package com.olp.paymentservice.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.olp.paymentservice.dto.StripeChargeDto;
import com.olp.paymentservice.model.Payment;
import com.olp.paymentservice.repository.PaymentRepository;
import com.olp.paymentservice.service.StripeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class StripeController {

    private final StripeService stripeService;
    private final PaymentRepository paymentRepository;

    @PostMapping("/charge")
    @ResponseBody
    public StripeChargeDto charge(@RequestBody StripeChargeDto model) {

        return stripeService.charge(model);
    }

    @GetMapping("/payments")
    @ResponseBody
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @GetMapping("/payments/{id}")
    @ResponseBody
    public ResponseEntity<Payment> getPaymentById(@PathVariable(value = "id") String paymentId) {
    Payment payment = paymentRepository.findById(paymentId)
            .orElseThrow(() -> new NoSuchElementException("Payment not found with id: " + paymentId));
        return ResponseEntity.ok().body(payment);
    }
    
}
