package com.capstone.capstoneProject.login.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.capstoneProject.login.entities.UserLoginDetails;

public interface UserLoginRepository extends JpaRepository<UserLoginDetails, Integer>{

	UserLoginDetails findByUsername(String username);
}
