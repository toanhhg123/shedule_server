package com.example.schedule.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.schedule.annotation.IsAdmin;
import com.example.schedule.core.ResponseObject;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping(path = "")
	@IsAdmin
	public ResponseEntity<?> get() {
		var res = new ResponseObject("success", "query user success", userRepository.findAll());
		return ResponseEntity.ok(res);
	}
}
