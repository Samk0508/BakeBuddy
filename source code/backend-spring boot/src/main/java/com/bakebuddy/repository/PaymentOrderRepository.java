package com.bakebuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakebuddy.model.PaymentOrder;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder,Long> {

    PaymentOrder findByPaymentLinkId(String paymentId);
}
