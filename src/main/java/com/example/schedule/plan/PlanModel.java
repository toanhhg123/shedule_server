package com.example.schedule.plan;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.example.schedule.companionUnit.CompanionUnitModel;
import com.example.schedule.organizationalUnit.OrganizationalUnitModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("PlanModel")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PlanModel {
	@Id
	String id;

	String title;

	String content;

	Date timeStart;

	String organizationalId;

	String companionUnitId;

	@Transient
	OrganizationalUnitModel organizationalUnitModel;

}
