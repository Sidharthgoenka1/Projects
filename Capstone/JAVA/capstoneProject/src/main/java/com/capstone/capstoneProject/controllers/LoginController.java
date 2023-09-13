package com.capstone.capstoneProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.capstoneProject.login.AuthenticationResponse;
import com.capstone.capstoneProject.login.LoginRequest;
import com.capstone.capstoneProject.login.RegisterRequest;
import com.capstone.capstoneProject.login.UserLoginService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {
	
	@Autowired
	private UserLoginService userLoginService;

	@PostMapping("/register")
	public Boolean register(@RequestBody RegisterRequest request){
		
		return userLoginService.register(request);
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request){
		return ResponseEntity.ok(userLoginService.login(request));
	}
	
	@PostMapping("/verify/credentials")
	public Boolean validate(@RequestBody LoginRequest request){
		return userLoginService.validateCredentials(request);
	}
	
	@GetMapping("/roles/list")
	public List<String> getRolesList(){
		return userLoginService.getUserRoles();
	}
}
