package com.kulkarniGroups.OrderManagementSystem.repository;

import com.kulkarniGroups.OrderManagementSystem.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity,Long> {

    /*
    @Modifying is the flag that tells Spring Data: "this query changes data, run it the other way."
     Without it,Spring throws an InvalidDataAccessApiUsageException at runtime for that UPDATE query.
     */

    @Query("""
       SELECT i.inventoryQty
       FROM InventoryEntity i
       WHERE i.inventoryProductName = :productName
       """)
    Integer getInventoryQty(String productName);


    /*
    A @Modifying query bypasses the normal JPA persistence-context/dirty-checking flow and
    hits the database directly with a raw UPDATE.
    Because of that, Spring requires it to run inside an active transaction
     */
    @Modifying
    @Query("""
       UPDATE InventoryEntity i
       SET i.inventoryQty = :qty
       WHERE i.inventoryProductName = :productName
       """)
    void updateInventoryQty(String productName, Integer qty);
}
