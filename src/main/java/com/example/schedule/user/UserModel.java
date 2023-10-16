package com.example.schedule.user;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.schedule.role.ERole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("Users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserModel {
	@Id
	String id;

	String userName;

	@Indexed(unique = true)
	String email;

	@JsonIgnore
	String password;

	private Set<ERole> roles;
}
