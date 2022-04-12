package com.krugercorp.employeesvaccination.commons.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
	private String name;
	private String lastName;
	private String role;
	private String country;
	private String uid;

}
