package com.example.demo17.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo17.config.JWTService;
import com.example.demo17.dto.DashboardResponse;
import com.example.demo17.dto.LoginRequest;
import com.example.demo17.dto.LoginResponse;
import com.example.demo17.dto.SignupRequest;
import com.example.demo17.dto.SignupResponse;
import com.example.demo17.service.LoginService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
    @Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private LoginService loginService;

    @PostMapping("/doLogin")
	public ResponseEntity<LoginResponse> doLogin(@RequestBody LoginRequest request) {
		LoginResponse response = new LoginResponse();

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		if (authentication.isAuthenticated()) {
			response.setToken(jwtService.generateToken(request.getUsername()));
		}

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/dashboard")
	public ResponseEntity<DashboardResponse> dashboard() {
		DashboardResponse response = new DashboardResponse();
		response.setResponse("Success");
		
		System.out.println("Dashboard Response");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/doRegister")
	public ResponseEntity<SignupResponse> doRegister(@RequestBody SignupRequest request) {
		return new ResponseEntity<>(loginService.doRegister(request), HttpStatus.CREATED);
	}
}
