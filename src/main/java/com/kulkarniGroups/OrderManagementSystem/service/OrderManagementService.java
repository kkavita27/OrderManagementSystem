package com.kulkarniGroups.OrderManagementSystem.service;

import com.kulkarniGroups.OrderManagementSystem.POJO.OrderPOJO;
import com.kulkarniGroups.OrderManagementSystem.entity.OrderEntity;
import com.kulkarniGroups.OrderManagementSystem.exceptions.OrderIdNotFoundException;
import com.kulkarniGroups.OrderManagementSystem.exceptions.OrderNameNotFoundException;
import com.kulkarniGroups.OrderManagementSystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderManagementService
{
    /*
     Client → sends JSON
        ↓
    Controller → OrderPOJO (request)
        ↓
    Service → convert → OrderEntity   (for DB)
        ↓
    Repository → save(Entity)
        ↓
    Service → convert → OrderPOJO    (for response)
        ↓
    Controller → JSON response
     */

    //Thumb Rule to remember: You fetch the list first because Repository only returns Entities, not POJOs.


    @Autowired
    private OrderRepository orderRepository;
    //Service layer talks to repository layer.

    public OrderPOJO createOrder(OrderPOJO order)
    {
        //Converted the OrderPOJO object into the entity. This POJO is taken from controller class and passed to Service layer.
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(order.getOrderId());
        orderEntity.setOrderName(order.getOrderName());
        orderEntity.setOrderDate(order.getOrderDate());
        orderEntity.setOrderQty(order.getOrderQty());
        orderEntity.setOrderPrice(order.getOrderPrice());
        orderEntity.setOrderStatus(order.getOrderStatus());
        orderRepository.save(orderEntity); // // returns OrderEntity, Entity is saved to DB via repository
        order.setOrderId(orderEntity.getOrderId()); // This will return the orderID into the order object
        return order;
    }

    public List<OrderPOJO> getAllOrders(Pageable pageable)
        {
            // 1. Fetch all entities from DB; DB → Entity → convert → POJO → return
//            Earlier implementation without Pageable.
//            List<OrderEntity> entities = orderRepository.findAll();
//            After implementing pagable
            Page<OrderEntity> entities = orderRepository.findAll(pageable);

            // 2. Create a list for POJO response
            List<OrderPOJO> pojoList = new ArrayList<>();

            // 3. Convert each Entity → POJO
            for (OrderEntity entity : entities)
            {
                OrderPOJO pojo = new OrderPOJO();
                pojo.setOrderId(entity.getOrderId());

                pojoList.add(pojo);  //  add POJO to POJO list
            }

            // 4. Return POJO list
            return pojoList;
        }


    public OrderPOJO findByOrderId(Long orderId) throws OrderIdNotFoundException{
        Optional<OrderEntity> orders = orderRepository.findById(orderId);
        /*
        Why does findById() return Optional?
        Because ID is unique, orderId → Primary Key OR Only ONE record can exist for that ID
        So result can be:
        Case	Result
        Found	One OrderEntity
        Not found Nothing
         */
        /*
        Optional is used in return types to explicitly indicate that a value may be absent, avoiding null-related issues.
        It is not used in Entity fields because JPA/Hibernate do not support it, it breaks serialization and JavaBean conventions, and adds unnecessary complexity.
         */
        if(orders.isPresent())
        {
            OrderEntity entity =  orders.get();
            OrderPOJO findByOrderIDPOJO = new OrderPOJO();
            findByOrderIDPOJO.setOrderId(entity.getOrderId());
            return findByOrderIDPOJO;
        } else {
            throw new OrderIdNotFoundException("Order not found, orderId: " + orderId);
        }
    }


    public OrderPOJO findByOrderName(String orderName) throws OrderNameNotFoundException {

        OrderEntity entity = orderRepository.findByOrderName(orderName);


        OrderPOJO pojo = new OrderPOJO();
        pojo.setOrderId(entity.getOrderId());


        return pojo;
    }

    public OrderPOJO deleteOrder(Long orderId)
    {

        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(orderEntity);
        OrderPOJO pojo = new OrderPOJO();
        pojo.setOrderId(orderEntity.getOrderId());
        pojo.setOrderName(orderEntity.getOrderName());
        pojo.setOrderDate(orderEntity.getOrderDate());
        pojo.setOrderStatus(orderEntity.getOrderStatus());
        pojo.setOrderPrice(orderEntity.getOrderPrice());

        return pojo;
    }
}
