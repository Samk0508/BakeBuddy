package com.bakebuddy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bakebuddy.dto.responce.ApiResponse;
import com.bakebuddy.entites.BakeryOwner;
import com.bakebuddy.entites.Order;
import com.bakebuddy.enums.OrderStatus;
import com.bakebuddy.exception.BakeryOwnerException;
import com.bakebuddy.exception.OrderException;
import com.bakebuddy.service.BakeryOwnerService;
import com.bakebuddy.service.OrderService;


@RestController
@RequestMapping("/owner/orders")
public class BakeryOwnerOrderController {
	@Autowired
    private  OrderService orderService;
	@Autowired
    private  BakeryOwnerService bakeryOwnerService;
   

    @GetMapping()
    public ResponseEntity<List<Order>> getAllOrdersHandler(
            @RequestHeader("Authorization") String jwt
    ) throws BakeryOwnerException {
    	BakeryOwner bakeryOwner=bakeryOwnerService.getBakeryOwnerProfile(jwt);
        List<Order> orders=orderService.getShopsOrders(bakeryOwner.getId());

        return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{orderId}/status/{orderStatus}")
    public ResponseEntity<Order> updateOrderHandler(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long orderId,
            @PathVariable OrderStatus orderStatus
    ) throws OrderException {

        Order orders=orderService.updateOrderStatus(orderId,orderStatus);

        return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/{orderId}/delete")
    public ResponseEntity<ApiResponse> deleteOrderHandler(@PathVariable Long orderId,
                                                          @RequestHeader("Authorization") String jwt) throws OrderException{
        orderService.deleteOrder(orderId);
        ApiResponse res=new ApiResponse("Order Deleted Successfully",true);
        System.out.println("delete method working....");
        return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
    }

}
