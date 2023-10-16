package com.example.schedule.companionUnit;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Generated
@Getter
@Setter
@Document("CompanionUnitModel")
public class CompanionUnitModel {
	@Id
	private String _id;
	private String name;
}
