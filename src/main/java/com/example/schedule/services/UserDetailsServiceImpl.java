package com.example.schedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.schedule.auth.UserDetailsImpl;
import com.example.schedule.user.UserModel;
import com.example.schedule.user.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel user = userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("not found email"));

		return UserDetailsImpl.build(user);
	}

	public UserDetails loadUserById(String id) throws UsernameNotFoundException {
		UserModel user = userRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("not found email"));

		return UserDetailsImpl.build(user);
	}

}
