package com.example.schedule.organizationalUnit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
@Data
@NoArgsConstructor
public class OrganizationalUnitDto {
	String name;
}
