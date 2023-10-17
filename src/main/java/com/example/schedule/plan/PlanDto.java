package com.example.schedule.plan;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
public class PlanDto {
	String title;

	String content;

	Date timeStart;

	String organizationalId;

	String companionUnitId;
}
