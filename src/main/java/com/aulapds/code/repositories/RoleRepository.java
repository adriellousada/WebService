package com.aulapds.code.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aulapds.code.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}