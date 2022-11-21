package com.horatiuhorvat.springLogInRegistrationProject.service;

import com.horatiuhorvat.springLogInRegistrationProject.persistence.UserEntity;
import com.horatiuhorvat.springLogInRegistrationProject.requests.UserRegistrationRequest;

public interface UserService {

	public void registerUser(UserRegistrationRequest user);
	
	
}
