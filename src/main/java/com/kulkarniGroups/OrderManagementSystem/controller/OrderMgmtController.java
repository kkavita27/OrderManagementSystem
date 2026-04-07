package com.kulkarniGroups.OrderManagementSystem.controller;

import com.kulkarniGroups.OrderManagementSystem.POJO.OrderPOJO;
import com.kulkarniGroups.OrderManagementSystem.entity.OrderEntity;
import com.kulkarniGroups.OrderManagementSystem.exceptions.OrderIdNotFoundException;
import com.kulkarniGroups.OrderManagementSystem.service.OrderManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
public class OrderMgmtController {

//    @Autowired
//    OrderManagementService orderManagementService;
//    //Controller talks to service layer.


    private final OrderManagementService orderManagementService;


    //Why parameterised constructor is introduced here.
    public OrderMgmtController(OrderManagementService orderManagementService) {
        this.orderManagementService = orderManagementService;
    }



    @PostMapping("/orders")
    public OrderPOJO createOrder(@Valid @RequestBody OrderPOJO orderPOJO)
    {
        return  orderManagementService.createOrder(orderPOJO);
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
    public List<OrderPOJO> getOrders(
         @RequestParam(value = "orderId", required = false) Long orderId,
         @RequestParam(value = "orderName", required = false) String orderName) throws OrderIdNotFoundException
    {



        List<OrderPOJO> orders = orderManagementService.getAllOrders();
        if(orders.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(orders).getBody();
        }  if (orderId == null && (orderName == null || orderName.isEmpty())) {
                return  orderManagementService.getAllOrders();
        }  if(orderId != null) {
            OrderPOJO orders1 = orderManagementService.findByOrderId(orderId);
            return List.of(orders1);
        }
        {
            OrderPOJO order = orderManagementService.findByOrderName(orderName);
            return List.of(order);
        }
    }




}
