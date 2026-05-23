package com.kulkarniGroups.OrderManagementSystem.repository;

import com.kulkarniGroups.OrderManagementSystem.entity.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;



//create interface
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long>{
    /*
    JPA (Java Persistence API) is a specification that defines how to manage relational data in Java applications.
    It acts as a bridge between Java objects and relational databases, allowing developers to interact with databases using object-oriented principles.
    JPA itself does not provide an implementation but relies on providers like Hibernate to execute its functionality.
     */

    //This is called JPA query
    //@Query("SELECT o from Orders o where o.orderName =:orderName")
    @Query("SELECT o from OrderEntity o where o.orderName =:orderName")
    OrderEntity findByOrderName(String orderName);
}
