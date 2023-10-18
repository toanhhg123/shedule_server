package com.example.schedule.userWork;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Data
@Builder
@Document("UserWorkModel")
public class UserWorkModel {
	@Id
	String id;

	boolean confirm;
	String userId;
	String status;
	String planId;
}
