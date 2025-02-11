package com.example.demo17.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo17.dto.LoginRequest;
import com.example.demo17.dto.SignupRequest;
import com.example.demo17.dto.SignupResponse;
import com.example.demo17.dto.User;
import com.example.demo17.repository.LoginRepository;

@Service
public class LoginService {
    @Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public String doLogin(LoginRequest request) {
		Optional<User> users = loginRepository.findByUsername(request.getUsername());

		if (users.isPresent()) {
			return "User details found";
		}

		return "User details not found";
	}
	
	public SignupResponse doRegister(SignupRequest request) {
		Optional<User> users = loginRepository.findByUsername(request.getUsername());
		
		SignupResponse response = new SignupResponse();
		
		if (users.isPresent()) {
			response.setResponse("User details Already found");
			return response;
		}
		
		User user = new User();
		user.setAddress(request.getAddress());
		user.setMobileNo(request.getMobileno());
		user.setName(request.getName());
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		
		loginRepository.save(user);
		
		response.setResponse("User created with id " + user.getId());

		return response;
	}

}
