package com.example.schedule.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.schedule.annotation.IsAdmin;
import com.example.schedule.core.ResponseObject;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@GetMapping(path = "")
	@IsAdmin
	public ResponseEntity<?> get() {
		var res = new ResponseObject("success", "query user success", userRepository.findAll());
		return ResponseEntity.ok(res);
	}

	@PostMapping(path = "")
	@IsAdmin
	public ResponseEntity<?> create(@Valid @RequestBody UserDto userDto) {
		var user = UserModel.builder().userName(userDto.userName).email(userDto.email)
				.password(encoder.encode(userDto.password)).roles(userDto.getRoles()).build();
		var res = new ResponseObject("success", "query user success", userRepository.insert(user));
		return ResponseEntity.ok(res);
	}

	@DeleteMapping(path = "{id}")
	@IsAdmin
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		userRepository.deleteById(id);
		return ResponseEntity.ok(id);
	}

}
