package com.kulkarniGroups.OrderManagementSystem.Repository;

import com.kulkarniGroups.OrderManagementSystem.Entity.Orders;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;



//create interface
@Repository
public interface OrderRepository extends JpaRepository<Orders,Long>{

}
