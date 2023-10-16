package com.example.schedule.auth;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.schedule.core.ResponseObject;
import com.example.schedule.exception.NoSuchElementFoundException;
import com.example.schedule.role.RoleRepository;
import com.example.schedule.security.jwt.JwtUtils;
import com.example.schedule.user.UserModel;
import com.example.schedule.user.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/login")
	ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		var jwtRes = new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getEmail(),
				roles);
		return ResponseEntity.ok(
				new ResponseObject("success", "login success", jwtRes));

	}

	@GetMapping("/me")
	ResponseEntity<?> getMe() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		Optional<UserModel> user = userRepository.findById(userDetails.id);

		if (!user.isPresent())
			throw new NoSuchElementFoundException("not found user");

		return ResponseEntity.ok(
				new ResponseObject("success", "login success", UserDetailsImpl.build(user.get())));

	}
}
