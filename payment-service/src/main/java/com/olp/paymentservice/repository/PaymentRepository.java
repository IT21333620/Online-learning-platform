package com.olp.paymentservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.olp.paymentservice.model.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String>{

}
