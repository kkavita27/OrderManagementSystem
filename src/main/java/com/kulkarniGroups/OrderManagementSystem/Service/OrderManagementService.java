package com.kulkarniGroups.OrderManagementSystem.Service;

import com.kulkarniGroups.OrderManagementSystem.Entity.Orders;
import com.kulkarniGroups.OrderManagementSystem.Exceptions.OrderIdNotFoundException;
import com.kulkarniGroups.OrderManagementSystem.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    public List<Orders> getAllOrders() {
        return  orderRepository.findAll();
    }

    public Orders findByOrderId(Long orderId) throws OrderIdNotFoundException{
        Optional<Orders> orders = orderRepository.findById(orderId);
        if(orders.isPresent())
        {
            return orders.get();
        } else {
            throw new OrderIdNotFoundException("Order not found, orderId: " + orderId);
        }
    }


    public Orders findByOrderName(String orderName) {
        return  orderRepository.findByOrderName(orderName);
    }
}
