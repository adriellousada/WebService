package com.aulapds.code.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aulapds.code.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
