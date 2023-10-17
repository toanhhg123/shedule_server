package com.example.schedule.user;

import java.util.Set;

import com.example.schedule.role.ERole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class UserDto {
	String userName;
	String email;
	String password;
	private Set<ERole> roles;
}
