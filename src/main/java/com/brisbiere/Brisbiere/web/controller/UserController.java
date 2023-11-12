package com.brisbiere.Brisbiere.web.controller;


import com.brisbiere.Brisbiere.domain.Order;
import com.brisbiere.Brisbiere.domain.User;
import com.brisbiere.Brisbiere.domain.service.OrderService;
import com.brisbiere.Brisbiere.domain.service.UserService;
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
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/{id}")
    @Operation(description = "Get account")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<User> getUser(@Parameter @PathVariable("id") int userId){
        return userService.getUser(userId).map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping("/email/{email}")
    @Operation(description = "Get account")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Beer not found")
    })
    public ResponseEntity<User> getUserByEmail(@Parameter @PathVariable("email") String email){
        return userService.getUserByemail(email).map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(description = "Delete a user by id from catalog.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<String> deleteUser(@Parameter @PathVariable("id") int userId){
        return userService.delete(userId) ?
                new ResponseEntity<>("User: Deleted",HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/order/{id}")
    @Operation(description = "Search all orders by userId.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<List<Order>> getOrdersByClient(@Parameter @PathVariable("id") int userId){
        return orderService.getByClient(userId)
                .map(orders -> new ResponseEntity<>(orders ,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
