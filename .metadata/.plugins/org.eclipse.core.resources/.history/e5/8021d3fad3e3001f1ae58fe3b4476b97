package com.bakebuddy.service;

import java.util.Set;

import com.bakebuddy.entites.Order;
import com.bakebuddy.entites.PaymentOrder;
import com.bakebuddy.entites.User;


public interface PaymentService {

    PaymentOrder createOrder(User user,
                             Set<Order> orders);

    PaymentOrder getPaymentOrderById(Long id) throws Exception;

    PaymentOrder getPaymentOrderByPaymentId(String paymentId) throws Exception;

//    Boolean ProceedPaymentOrder (PaymentOrder paymentOrder,
//                                 String paymentId, String paymentLinkId) throws RazorpayException;

//    PaymentLink createRazorpayPaymentLink(User user,
//                                          Long Amount,
//                                          Long orderId) throws RazorpayException;
//
//    String createStripePaymentLink(User user, Long Amount,
//                                            Long orderId) throws StripeException;
}
