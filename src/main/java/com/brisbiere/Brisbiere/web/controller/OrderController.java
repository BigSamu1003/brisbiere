package com.brisbiere.Brisbiere.web.controller;


import com.brisbiere.Brisbiere.domain.Order;
import com.brisbiere.Brisbiere.domain.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    @Operation(description = "Get all purchases.")
    @ApiResponse(responseCode = "200", description = "Ok.")
    public ResponseEntity<List<Order>> getAll(){
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(description = "Search all orders by userId.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<List<Order>> getByClient(@Parameter @PathVariable("id") int userId){
        return orderService.getByClient(userId)
                .map(orders -> new ResponseEntity<>(orders ,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping("/email/{email}")
    @Operation(description = "Search all orders by userEmail.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<List<Order>> getByClientEmail(@Parameter @PathVariable("email") String email){
        return orderService.getByEmail(email)
                .map(orders -> new ResponseEntity<>(orders ,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/lastOrderId")
    public ResponseEntity<Integer> getLastOrderId(){
        return new ResponseEntity<>(this.orderService.getLastOrderId(), HttpStatus.OK);
    }

    @PostMapping("/save")
    @Operation(description = "Save an order with ful body.")
    @ApiResponse(responseCode = "200", description = "Ok")
    public ResponseEntity<Order> save(@RequestBody Order order){
        return new ResponseEntity<>(orderService.save(order), HttpStatus.OK);
    }
}
