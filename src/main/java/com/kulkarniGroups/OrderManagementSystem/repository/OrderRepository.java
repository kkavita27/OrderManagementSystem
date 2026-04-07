package com.kulkarniGroups.OrderManagementSystem.repository;

import com.kulkarniGroups.OrderManagementSystem.entity.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;



//create interface
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long>{

    //This is called JPA query
    //@Query("SELECT o from Orders o where o.orderName =:orderName")
    @Query("SELECT o from OrderEntity o where o.orderName =:orderName")
    OrderEntity findByOrderName(String orderName);
}
