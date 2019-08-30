package com.aulapds.code.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aulapds.code.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
