package com.aulapds.code.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aulapds.code.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
