package com.capstone.capstoneProject.login.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstone.capstoneProject.login.entities.UserDetailsMaster;

@Repository
public interface UserDetailsMasterRepository extends JpaRepository<UserDetailsMaster, Integer>{

	@Query("SELECT u FROM UserDetailsMaster u WHERE u.username = :username")
	UserDetailsMaster findByUsername(@Param("username") String username);

	@Query("SELECT u FROM UserDetailsMaster u WHERE u.id = :id")
	UserDetailsMaster getUserById(@Param("id") Integer id);

	@Query("SELECT u FROM UserDetailsMaster u WHERE u.id = :id and u.userType = :userType")
	UserDetailsMaster findUserByIdAndUserType(@Param("id") Integer id, @Param("userType") String userType);

}
