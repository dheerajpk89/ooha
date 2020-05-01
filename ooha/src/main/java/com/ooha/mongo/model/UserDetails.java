package com.ooha.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {

	private String registrationRype;

	private String emailId;

	private String password;

	private String firstName;
	private String middletName;

	private String lastName;

	private String mobile;

	private String address1;

	private String address2;

	private String country;

	private String province;

	private String createdDate;

	private String updateDate;

}
