package com.ooha.mongo.model;


import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor 
public class LoginModel {

	private String userId;
	
	private String password;

}
