package com.bakebuddy.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bakebuddy.dto.responce.ApiResponse;
import com.bakebuddy.dto.responce.PaymentLinkResponse;
import com.bakebuddy.entites.BakeryOwner;
import com.bakebuddy.entites.BakeryOwnerReport;
import com.bakebuddy.entites.Cart;
import com.bakebuddy.entites.Order;
import com.bakebuddy.entites.PaymentOrder;
import com.bakebuddy.entites.User;
import com.bakebuddy.enums.PaymentMethod;
import com.bakebuddy.repository.CartItemRepository;
import com.bakebuddy.repository.CartRepository;
import com.bakebuddy.service.BakeryOwnerReportService;
import com.bakebuddy.service.BakeryOwnerService;
import com.bakebuddy.service.PaymentService;
import com.bakebuddy.service.TransactionService;
import com.bakebuddy.service.UserService;


import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final UserService userService;
    private final PaymentService paymentService;
    private final TransactionService transactionService;
    private final BakeryOwnerReportService bakeryOwnerReportService;
    private final BakeryOwnerService bakeryOwnerService;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;


    @PostMapping("/api/payment/order/{orderId}")
    public ResponseEntity<PaymentLinkResponse> paymentHandler(
           // @PathVariable PaymentMethod paymentMethod,
            @PathVariable Long orderId,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserProfileByJwt(jwt);
        PaymentLinkResponse paymentResponse;
       

        PaymentOrder order= paymentService.getPaymentOrderById(orderId);
       //        if(paymentMethod.equals(PaymentMethod.RAZORPAY)){
//            paymentResponse=paymentService.createRazorpayPaymentLink(user,
//                    order.getAmount(),
//                    order.getId());
//        }
//        else{
//            paymentResponse=paymentService.createStripePaymentLink(user,
//                    order.getAmount(),
//                    order.getId());
//        }

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }


    @GetMapping("/api/payment/{paymentId}")
    public ResponseEntity<ApiResponse> paymentSuccessHandler(
            @PathVariable String paymentId,
            @RequestParam String paymentLinkId,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserProfileByJwt(jwt);

        PaymentLinkResponse paymentResponse;

        PaymentOrder paymentOrder= paymentService
                .getPaymentOrderByPaymentId(paymentLinkId);

        boolean paymentSuccess = paymentService.ProceedPaymentOrder(
                paymentOrder,
                paymentId,
                paymentLinkId
        );
        if(paymentSuccess){
            for(Order order:paymentOrder.getOrders()){
                transactionService.createTransaction(order);
                BakeryOwner bakeryOwner=bakeryOwnerService.getBakeryOwnerById(order.getBakeryDetails().getId());
                BakeryOwnerReport report=bakeryOwnerReportService.getBakeryOwnerReport(bakeryOwner);
                report.setTotalOrders(report.getTotalOrders()+1);
                report.setTotalEarnings(report.getTotalEarnings()+order.getTotalSellingPrice());
                report.setTotalSales(report.getTotalSales()+order.getOrderItems().size());
                bakeryOwnerReportService.updateBakeryOwnerReport(report);
            }
            Cart cart=cartRepository.findByUser_Id(user.getId());
            cart.setCouponPrice(0);
            cart.setCouponCode(null);
//        Set<CartItem> items=cart.getCartItems();
//        cartItemRepository.deleteAll(items);
//        cart.setCartItems(new HashSet<>());
            cartRepository.save(cart);

        }
      
        ApiResponse res = new ApiResponse();
        res.setMessage("Payment successful");
        res.setStatus(true);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
