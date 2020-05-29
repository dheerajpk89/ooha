package com.ooha.mongo.entity;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class UserEntity {

	@Id
	private String id;

	@Field("userID")
	private String userID;

	@NotEmpty(message = "Please Enter Valied Password")
	@Field("password")
	private String password;

	@NotEmpty(message = "Select Valied User Name")
	@Field("userType")
	private String userType;

	@NotEmpty(message = "Please Enter Valied First Name")
	@Field("firstName")
	private String firstName;

	@NotEmpty(message = "Please Enter Valied Last Name")
	@Field("lastName")
	private String lastName;

	@Pattern(regexp = "[0-9-\\\\+]+", message = "Please Enter Valied Phone Number")
	@Field("mobile")
	private String mobile;

	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Please Enter Valied Mail Id")
	@Field("emailId")
	private String emailId;

	@Field("address")
	private String address;

	@Field("country")
	private String country;

	@Field("createdDate")
	private String createdDate;

	@Field("updateDate")
	private String updateDate;

	@Field("isUserActive")
	@Builder.Default
	private Boolean isUserActive = false;
	
}
