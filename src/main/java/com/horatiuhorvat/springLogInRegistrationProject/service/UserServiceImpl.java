package com.horatiuhorvat.springLogInRegistrationProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horatiuhorvat.springLogInRegistrationProject.persistence.UserEntity;
import com.horatiuhorvat.springLogInRegistrationProject.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepo;

	@Override
	public void registerUser(UserEntity user) {
		this.userRepo.save(user);
	}

}
