package com.aulapds.code.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aulapds.code.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
