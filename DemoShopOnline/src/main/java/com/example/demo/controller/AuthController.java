package com.example.demo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ERole;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.payload.reponse.JwtResponse;
import com.example.demo.payload.reponse.MessageResponse;
import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.repository.RoleReponsitory;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenManager;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleReponsitory roleReponsitory;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
		Authentication authentication =authenManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getName(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt =jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails =(UserDetailsImpl) authentication.getPrincipal();
		List<String> roles =userDetails.getAuthorities().stream()
							.map(item -> item.getAuthority())
							.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getId(),
													userDetails.getUsername(),
													userDetails.getEmail(),roles));
	}
	

	
}
