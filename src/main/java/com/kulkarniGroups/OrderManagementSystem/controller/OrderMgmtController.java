package com.kulkarniGroups.OrderManagementSystem.controller;

import com.kulkarniGroups.OrderManagementSystem.POJO.OrderPOJO;
import com.kulkarniGroups.OrderManagementSystem.exceptions.OrderIdNotFoundException;
import com.kulkarniGroups.OrderManagementSystem.service.OrderManagementService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Objects;

@Controller
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
    public OrderPOJO createOrder(@Valid @RequestBody OrderPOJO orderPOJO, Model model)
    {
        model.addAttribute("orders",orderPOJO);
        return  orderManagementService.createOrder(orderPOJO);
    }


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
//    public List<OrderPOJO> getOrders(
//    Converted the above impl to
    public String getOrders(@PageableDefault( page = 0,size = 10,sort = "orderDate",direction = Sort.Direction.DESC)
            Pageable pageable,
            @RequestParam(value = "orderId", required = false)
            Long orderId,
            @RequestParam(value = "orderName", required = false)
            String orderName,
            Model model
    ) throws OrderIdNotFoundException
    {
        List<OrderPOJO> orders;


        if (orderId != null) {
            OrderPOJO order = orderManagementService.findByOrderId(orderId);
            orders = List.of(order);

        }
        else  if (orderName != null && !orderName.isEmpty()) {
            OrderPOJO order = orderManagementService.findByOrderName(orderName);
//            return List.of(order);
            orders = List.of(order); // OrderPOJO object from line 102 is passed to list of OrderPOJO
        }
        else {

            orders =
                    orderManagementService.getAllOrders(pageable);
        }
//        List<OrderPOJO> orders = orderManagementService.getAllOrders(pageable);
        /*
        The controller is only:
        receiving Pageable
        passing it to service

         The real pagination still happens when repository executes query using pageable
         */
        if (orders.isEmpty()) {
            return Objects.requireNonNull(ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(orders)
                    .getBody()).toString();
        }

        model.addAttribute("orders", orders);

        model.addAttribute("order", new OrderPOJO());

        //return orders; //Earlier OrderPOJO object.
        return "orders";
    }

    @DeleteMapping("orders/{orderId}")
    public OrderPOJO deleteOrder(@PathVariable Long orderId, Model model)
    {
        // Add attribute to the model
        model.addAttribute("message", "User deleted successfully");
        return orderManagementService.deleteOrder(orderId);

    }


}
