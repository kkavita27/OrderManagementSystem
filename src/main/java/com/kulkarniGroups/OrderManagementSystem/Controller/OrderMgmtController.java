package com.kulkarniGroups.OrderManagementSystem.Controller;

import com.kulkarniGroups.OrderManagementSystem.Entity.Orders;
import com.kulkarniGroups.OrderManagementSystem.Exceptions.OrderIdNotFoundException;
import com.kulkarniGroups.OrderManagementSystem.Service.OrderManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

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


//    @GetMapping("/orders")
//    public List<Orders> getCourse(@RequestParam(value="orderId", required=false) Long orderId)
//    {
//        if(orderId == null) {orderManagementService.getAllOrders();
//        }
//
//        Orders orders = orderManagementService.findByOrderId(orderId);
//        return List.of(orders);
//    }


    /*
    Since I was getting below error in console,     on hit of url in browser "http://localhost:8082/orders?orderId=5 "
     Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed: com.kulkarniGroups.OrderManagementSystem.Exceptions.OrderIdNotFoundException: Order not found, orderId: 5] with root cause

    com.kulkarniGroups.OrderManagementSystem.Exceptions.OrderIdNotFoundException: Order not found, orderId: 5
	at com.kulkarniGroups.OrderManagementSystem.Service.OrderManagementService.findByOrderId(OrderManagementService.java:36) ~[classes/:na]

    ✔ Interpretation
    A request came to a controller endpoint
    Controller called OrderManagementService.findByOrderId(5)
    Inside the service, no order with ID = 5 exists
    You explicitly threw OrderIdNotFoundException
    Spring did not catch it, so it bubbled up to DispatcherServlet
    Result: 500 Internal Server Error

    Solution: Create a @ControllerAdvice (Global Exception Handler)

     */


    //localised exception handler
    @ExceptionHandler(OrderIdNotFoundException.class)
    public ResponseEntity<String> handleLearnerNotFoundException(OrderIdNotFoundException e)
    {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        //HttpStatus.NOT_FOUND client error.
    }


    @GetMapping("/orders")
    public List<Orders> getOrders(
         @RequestParam(value = "orderId", required = false) Long orderId,
         @RequestParam(value = "orderName", required = false) String orderName) throws OrderIdNotFoundException {

        List<Orders> orders = orderManagementService.getAllOrders();
        if(orders.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(orders).getBody();
        }  if (orderId == null && (orderName == null || orderName.isEmpty())) {
                return  orderManagementService.getAllOrders();
        }  if(orderId != null) {
            Orders orders1 = orderManagementService.findByOrderId(orderId);
            return List.of(orders1);
        }
        {
            Orders order = orderManagementService.findByOrderName(orderName);
            return List.of(order);
        }
    }




}
