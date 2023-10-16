package com.example.schedule.role;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("Role")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RoleModel {
	@Id
	@Field("_id")
	private String _id;

	@Field("name")
	@Indexed(unique = true)
	private ERole name;
}
