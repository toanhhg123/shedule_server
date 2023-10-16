package com.example.schedule;

import java.util.List;
import java.util.Set;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.schedule.core.ResponseObject;
import com.example.schedule.role.ERole;
import com.example.schedule.role.RoleModel;
import com.example.schedule.role.RoleRepository;
import com.example.schedule.security.jwt.JwtUtils;
import com.example.schedule.user.UserModel;
import com.example.schedule.user.UserRepository;

@RestController
@RequestMapping(path = "/api/test")
public class TestController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@GetMapping("/seed-admin")
	ResponseEntity<ResponseObject> seedAdmin() {

		UserModel user = UserModel.builder()
				.userName("admin")
				.email("admin@gmail.com")
				.password(encoder.encode("12345678"))
				.roles(Set.of(ERole.ROLE_ADMIN, ERole.ROLE_USER))
				.build();

		ResponseObject res = new ResponseObject("success", "success", userRepository.insert(user));

		return ResponseEntity.status(HttpStatus.CREATED).body(res);

	}

	@GetMapping("/seed-user")
	ResponseEntity<ResponseObject> seedUser() {

		UserModel user = UserModel.builder()
				.userName("user")
				.email("user@gmail.com")
				.password(encoder.encode("12345678"))
				.roles(Set.of(ERole.ROLE_USER))
				.build();

		ResponseObject res = new ResponseObject("success", "success", userRepository.insert(user));

		return ResponseEntity.status(HttpStatus.CREATED).body(res);

	}

	@GetMapping("/seed-role")
	ResponseEntity<ResponseObject> seedRole() {

		RoleModel roleAdmin = RoleModel.builder()
				.name(ERole.ROLE_ADMIN)
				.build();

		RoleModel roleUser = RoleModel.builder()
				.name(ERole.ROLE_USER)
				.build();

		ResponseObject res = new ResponseObject(
				"success",
				"success",
				roleRepository.insert(List.of(roleAdmin, roleUser)));

		return ResponseEntity.status(HttpStatus.CREATED).body(res);

	}
}
