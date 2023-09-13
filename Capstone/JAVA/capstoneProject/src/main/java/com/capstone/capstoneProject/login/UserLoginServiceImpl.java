package com.capstone.capstoneProject.login;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capstone.capstoneProject.config.JwtService;
import com.capstone.capstoneProject.login.entities.ShopDetails;
import com.capstone.capstoneProject.login.entities.UserDetailsMaster;
import com.capstone.capstoneProject.login.entities.UserLoginDetails;
import com.capstone.capstoneProject.login.entities.WarehouseDetails;
import com.capstone.capstoneProject.login.repositories.ShopDetailsRepository;
import com.capstone.capstoneProject.login.repositories.UserDetailsMasterRepository;
import com.capstone.capstoneProject.login.repositories.UserLoginRepository;
import com.capstone.capstoneProject.login.repositories.WarehouseDetailsRepository;

@Service
public class UserLoginServiceImpl implements UserLoginService{

	@Autowired
	private UserLoginRepository userLoginRepository;
	
	@Autowired
	private WarehouseDetailsRepository warehouseDetailsRepository;
	
	@Autowired
	private ShopDetailsRepository shopDetailsRepository;
	
	@Autowired
	private UserDetailsMasterRepository userDetailsMasterRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Value("#{'${list.roles}'.split(',')}") 
	List<String> userRole;
	
	@Value("#{'${list.status_values}'.split(',')}") 
	List<String> orderStatus;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserLoginDetails loaduserByUsername(String username){
		// TODO Auto-generated method stub
		UserLoginDetails user = userLoginRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid Credentials");
		}
		return user;
	}

	@Override
	public Boolean register(RegisterRequest request){
		// TODO Auto-generated method stub
		
		if(userLoginRepository.findByUsername(request.getUsername()) == null) {
			UserLoginDetails user = new UserLoginDetails();
			user.setUsername(request.getUsername());
			user.setPassword(passwordEncoder.encode(request.getPassword()));
			user.setStatus("A");
			user.setRole(request.getRole());
			user.setLastUpdateDate(new Date());
			
			userLoginRepository.save(user);
			
			WarehouseDetails w;
			ShopDetails s;
			UserDetailsMaster u = new UserDetailsMaster();
			u.setAddress(request.getAddress());
			u.setContactNo(request.getContactNo());
			u.setEmail(request.getEmail());
			u.setName(request.getName());
			u.setUsername(request.getUsername());
			if(request.getUserType().equalsIgnoreCase("D")) {
				System.out.println("Setting warehouse details {"+request.toString()+"}");
				WarehouseDetails warehouseDetails = new WarehouseDetails();
				warehouseDetails.setWarehouseAddress(request.getWorkplaceAddress());
				warehouseDetails.setWarehouseContact(request.getWorkplaceContactNo());
				warehouseDetails.setWarehouseName(request.getWorkplaceName());
				w = warehouseDetailsRepository.save(warehouseDetails);
				System.out.println("Warehouse details saved {"+w.toString()+"}");
				u.setWorkplaceId(w.getId());
				u.setUserType("D");
			}else if(request.getUserType().equalsIgnoreCase("S")){
				ShopDetails shop = new ShopDetails();
				shop.setShopAddress(request.getWorkplaceAddress());
				shop.setShopContact(request.getWorkplaceContactNo());
				shop.setShopName(request.getWorkplaceName());
				s = shopDetailsRepository.save(shop);
				u.setWorkplaceId(s.getId());
				u.setUserType("S");
			}
			
			userDetailsMasterRepository.save(u);
			
			AuthenticationResponse response = new AuthenticationResponse();
			response.setToken(jwtService.generateToken(user));
			return true;
		}else {
			return false;
		}
		
		
	}

	@Override
	public AuthenticationResponse login(LoginRequest request) {
		// TODO Auto-generated method stub
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		
		UserLoginDetails user = userLoginRepository.findByUsername(request.getUsername());
		if(user == null) {
			throw new UsernameNotFoundException("Invalid Credentials");
		}
		
		AuthenticationResponse response = new AuthenticationResponse();
		response.setToken(jwtService.generateToken(user));
		return response;
	}

	@Override
	public List<String> getUserRoles() {
		// TODO Auto-generated method stub
		return userRole;
	}

	@Override
	public Boolean validateCredentials(LoginRequest request) {
		// TODO Auto-generated method stub
		UserLoginDetails user = userLoginRepository.findByUsername(request.getUsername());
		if(user != null) {
			return false;
		}
		return true;
	}

	@Override
	public RegisterRequest getUserDetails(String username) {
		// TODO Auto-generated method stub
		System.out.println("Getting user details for user name {"+username+"}");
		UserDetailsMaster user = userDetailsMasterRepository.findByUsername(username);
		RegisterRequest response = new RegisterRequest();
		response.setUsername(user.getUsername());
		response.setName(user.getName());
		response.setEmail(user.getEmail());
		response.setUserId(user.getId());
		response.setAddress(user.getAddress());
		response.setUserType(user.getUserType());
		response.setAddress(user.getAddress());
		response.setContactNo(user.getContactNo());
		if(user.getUserType().equalsIgnoreCase("S")) {
			ShopDetails w = shopDetailsRepository.getShopDetailsById(user.getWorkplaceId());
			response.setWorkplaceAddress(w.getShopAddress());
			response.setWorkplaceContactNo(w.getShopContact());
			response.setWorkplaceId(w.getId());
			response.setWorkplaceName(w.getShopName());
			response.setRole(userRole.get(1));
		}else {
			WarehouseDetails w = warehouseDetailsRepository.getWarehouseDetailsById(user.getWorkplaceId());
			response.setWorkplaceAddress(w.getWarehouseAddress());
			response.setWorkplaceContactNo(w.getWarehouseContact());
			response.setWorkplaceId(w.getId());
			response.setWorkplaceName(w.getWarehouseName());
			response.setRole(userRole.get(0));
		}
		
		return response;
	}

	@Override
	public List<String> getOrderStatus() {
		// TODO Auto-generated method stub
		return orderStatus;
	}

}
