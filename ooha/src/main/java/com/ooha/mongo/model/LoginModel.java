package com.ooha.mongo.model;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginModel {

	@NotEmpty(message = "Please Enter Valied userId")
	private String userId;

	@NotEmpty(message = "Please Enter Valied Password")
	private String password;

}
