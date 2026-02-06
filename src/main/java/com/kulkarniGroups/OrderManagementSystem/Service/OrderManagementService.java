package com.kulkarniGroups.OrderManagementSystem.Service;

import com.kulkarniGroups.OrderManagementSystem.Entity.Orders;
import com.kulkarniGroups.OrderManagementSystem.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderManagementService
{

    @Autowired
    private OrderRepository orderRepository;
    //Service layer talks to repository layer.

    public Orders createOrder(Orders order)
    {
        return orderRepository.save(order);
    }


}
