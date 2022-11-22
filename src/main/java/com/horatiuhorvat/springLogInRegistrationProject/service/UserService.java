package com.horatiuhorvat.springLogInRegistrationProject.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.horatiuhorvat.springLogInRegistrationProject.persistence.UserEntity;
import com.horatiuhorvat.springLogInRegistrationProject.requests.UserRegistrationRequest;

public interface UserService extends UserDetailsService {

	public void registerUser(UserRegistrationRequest user);
	public void registerAdmin(UserRegistrationRequest user, String adminCode);
	
	
}
