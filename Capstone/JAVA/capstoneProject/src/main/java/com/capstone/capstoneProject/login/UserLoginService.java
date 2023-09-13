package com.capstone.capstoneProject.login;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.capstone.capstoneProject.login.entities.UserLoginDetails;

public interface UserLoginService extends UserDetailsService{

	UserLoginDetails loaduserByUsername(String username);

	Boolean register(RegisterRequest request);

	AuthenticationResponse login(LoginRequest request);

	List<String> getUserRoles();
	
	Boolean validateCredentials(LoginRequest request);
	
	RegisterRequest getUserDetails(String username);
	
	List<String> getOrderStatus();
}
