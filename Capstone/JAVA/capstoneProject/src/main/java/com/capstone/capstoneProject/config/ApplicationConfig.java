package com.capstone.capstoneProject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.capstone.capstoneProject.login.repositories.UserLoginRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

//	@Autowired
//	private UserLoginService userLoginService;
	
	@Autowired
	private UserLoginRepository userLoginRepository;
	
	@Bean
	public UserDetailsService userDetailsService() {
		
//		return new UserDetailsService() {
//			
//			@Override
//			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//				// TODO Auto-generated method stub
//				System.out.println("Username -> {}"+username);
//				UserDetails user = userLoginRepository.findByUsername(username);
//				System.out.println("Pojo -> "+user.toString());
//				return user;
//			}
//		};
		
		return username -> userLoginRepository.findByUsername(username);
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		//data access object for fetching user details and encode decode password
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncode());
		return authProvider;
	}

	@Bean
	public PasswordEncoder passwordEncode() {
		// TODO Auto-generated method stub
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
		
		return config.getAuthenticationManager();
		
	}
	
//	  @Autowired
//	  public void configureAuthManager(final AuthenticationManagerBuilder authMB, @Qualifier("daoAuthenticationProvider") AuthenticationProvider provider) {
//	    authMB.authenticationProvider(provider);
//	  }
}
