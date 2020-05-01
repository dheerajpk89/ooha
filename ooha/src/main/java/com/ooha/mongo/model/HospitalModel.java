package com.ooha.mongo.model;

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
@Document(collection = "hospital")
public class HospitalModel {
	@Id
	private String id;

	@NotEmpty(message = "Please Enter Valied Hospital Name")
	@Field("hospitalName")
	private String hospitalName;

	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="Please Enter Valied Hospital Mail Id")
	@Field("hospitalEmailId")
	private String hospitalEmailId;

	@Field("hospitalPhoneNumber")
	@Pattern(regexp = "[0-9-\\\\+]+", message="Please Enter Valied Phone Number")
	private String hospitalPhoneNumber;

	@Field("hospitalAddress")
	private String hospitalAddress;

	@Field("hospitalCountry")
	private String hospitalCountry;

	@Field("hospitalCreatedDate")
	private String createdDate;

	@Field("hospitalUpdateDate")
	private String updateDate;

	@Field("isHospitalActive")
	@Builder.Default
	private Boolean isHospitalActive = false;

}
