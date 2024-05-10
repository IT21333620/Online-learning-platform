package com.olp.stripeservice.controller;

import com.olp.stripeservice.dto.StripeChargeDto;
import com.olp.stripeservice.model.Payment;
import com.olp.stripeservice.repository.PaymentRepository;
import com.olp.stripeservice.service.StripeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
