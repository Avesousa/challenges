package com.mavha.challenge.backend.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mavha.challenge.backend.helper.Response;
import com.mavha.challenge.backend.helper.TokenAuthenticator;
import com.mavha.challenge.backend.model.User;
import com.mavha.challenge.backend.service.UserService;

@RestController
@RequestMapping("api/auth")
public class AuthController {

	@Autowired
	private UserService userService;	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user){
		try {
			User userLog = userService.getByEmail(user.getEmail());
			return ResponseEntity.ok(TokenAuthenticator.generate(userLog));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(getResponseError(409, Response.LOGIN_NOT_FOUND));
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user){
		try {
			User userLog = userService.save(user);
			return ResponseEntity.ok(TokenAuthenticator.generate(userLog));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(getResponseError(409, Response.LOGIN_NOT_FOUND));
		}
	}
		
		
	private HashMap<String,Object> getResponseError(int error, String message){
		HashMap<String,Object> response = new HashMap<>();
		response.put("error", error);
		response.put("message", message);
		return response;
	}
	
}
