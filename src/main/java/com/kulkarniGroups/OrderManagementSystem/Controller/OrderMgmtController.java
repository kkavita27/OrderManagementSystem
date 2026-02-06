package com.kulkarniGroups.OrderManagementSystem.Controller;

import com.kulkarniGroups.OrderManagementSystem.Entity.Orders;
import com.kulkarniGroups.OrderManagementSystem.Service.OrderManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
public class OrderMgmtController {

    @Autowired
    OrderManagementService orderManagementService;
    //Controller talks to service layer.


    @GetMapping("/")
    public String HelloWorld()
    {  //Simple end point
        return "Hello Kavita!";
    }

    @GetMapping("/health")
    public String Health()
    {
        return "this is health api ";
    }

    @PostMapping("/orders")
    public Orders createOrder(@Valid @RequestBody Orders orders)
    {
        return  orderManagementService.createOrder(orders);
    }




}
