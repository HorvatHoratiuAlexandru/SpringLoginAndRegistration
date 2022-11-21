package com.horatiuhorvat.springLogInRegistrationProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.horatiuhorvat.springLogInRegistrationProject.requests.UserRegistrationRequest;
import com.horatiuhorvat.springLogInRegistrationProject.service.UserService;
import com.horatiuhorvat.springLogInRegistrationProject.service.UserServiceImpl;


@RestController
@RequestMapping("/")
public class MainController {
	
	@Autowired
	UserService userService;

	@PostMapping("/login")
	public String logIn() {
		
		
		return "succesfull login";
	}
	
	@PostMapping("/register")
	public String register(@RequestBody UserRegistrationRequest userRegistrationReq) {
		
		this.userService.registerUser(userRegistrationReq);

		return "user registered succesfully";
	}
}
