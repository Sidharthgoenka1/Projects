package com.capstone.capstoneProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.capstoneProject.login.RegisterRequest;
import com.capstone.capstoneProject.login.UserLoginService;


@RestController
@RequestMapping("/master")
public class UserController {
	
	@Autowired
	private UserLoginService userLoginService;

	@GetMapping("/get")
	public String gettest() {
		return "Test";
	}
	
	@PostMapping("/user/details/{username}")
	public RegisterRequest getUserDetails(@PathVariable("username") String username){
		return userLoginService.getUserDetails(username);
	}
	
	
	@GetMapping("/order/status/list")
	public List<String> getStatusList(){
		return userLoginService.getOrderStatus();
	}
}
