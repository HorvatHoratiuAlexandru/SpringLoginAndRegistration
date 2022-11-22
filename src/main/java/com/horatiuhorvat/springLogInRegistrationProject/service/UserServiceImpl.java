package com.horatiuhorvat.springLogInRegistrationProject.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	Utils appUtils;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@Override
	public void registerUser(UserRegistrationRequest user) {
		UserEntity userToRegister = new UserEntity();
		
		BeanUtils.copyProperties(user, userToRegister);
		
		userToRegister.setUserId(appUtils.generateUserId(16));
		
		userToRegister.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		userToRegister.setRole("USER");
		
		this.userRepo.save(userToRegister);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepo.findByEmail(username);
		UserDetails returnUser = User.builder()
				.password(userEntity.getEncryptedPassword())
				.username(username)
				.authorities("ROLE_" + userEntity.getRole())
				.build();
		
		return returnUser;
	}

	@Override
	public void registerAdmin(UserRegistrationRequest user, String adminCode) {
		
		if(appUtils.checkAdminSecret(adminCode)) {
			
			UserEntity userToRegister = new UserEntity();
			
			BeanUtils.copyProperties(user, userToRegister);
			
			userToRegister.setUserId(appUtils.generateUserId(16));
			
			userToRegister.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			
			userToRegister.setRole("ADMIN");
			
			this.userRepo.save(userToRegister);
		}else {
			throw new BadCredentialsException("invalid admincode");
		}
		
		
	}

}
