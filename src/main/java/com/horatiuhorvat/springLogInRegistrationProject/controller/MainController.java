package com.horatiuhorvat.springLogInRegistrationProject.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

	@GetMapping("/privateUser")
	public String userPrivate() {
		
		
		return "user private";
	}
	
	@GetMapping("/privateAdmin")
	public String adminPrivate() {
		
		
		return "admin private";
	}
	
	@PostMapping("/register")
	public String register(@RequestBody UserRegistrationRequest userRegistrationReq,
			@RequestHeader Map<String, String> allHeaders) {
		System.out.println(allHeaders);
		if(allHeaders.containsKey("admincode")) {
			this.userService.registerAdmin(userRegistrationReq, allHeaders.get("admincode"));
		}else {
			this.userService.registerUser(userRegistrationReq);
		}

		return "user registered succesfully";
	}
}
