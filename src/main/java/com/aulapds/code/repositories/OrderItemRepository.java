package com.aulapds.code.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aulapds.code.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
