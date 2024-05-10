package com.olp.stripeservice.repository;

import com.olp.stripeservice.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {
}
