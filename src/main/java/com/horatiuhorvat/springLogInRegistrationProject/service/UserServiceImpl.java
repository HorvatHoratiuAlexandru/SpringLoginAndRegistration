package com.horatiuhorvat.springLogInRegistrationProject.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horatiuhorvat.springLogInRegistrationProject.persistence.UserEntity;
import com.horatiuhorvat.springLogInRegistrationProject.repository.UserRepository;
import com.horatiuhorvat.springLogInRegistrationProject.requests.UserRegistrationRequest;
import com.horatiuhorvat.springLogInRegistrationProject.utils.Utils;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepo;
	@Autowired
	Utils utils;

	@Override
	public void registerUser(UserRegistrationRequest user) {
		UserEntity userToRegister = new UserEntity();
		
		BeanUtils.copyProperties(user, userToRegister);
		
		userToRegister.setUserId(utils.generateUserId(16));
		
		userToRegister.setEncryptedPassword("encryptedPassword");
		
		this.userRepo.save(userToRegister);
	}

}
