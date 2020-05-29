package com.ooha.mongo.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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
@Document(collection = "token")
public class TokenEntity {
	@Id
	private String id;
	
	@Field("userOjectId")
	private String userOjectId;

	@Field("userID")
	private String userID;
	
	@Field("roleType")
	private String roleType;

	@Field("createdDate")
	private String createdDate;

	@Indexed(name = "timeToLeave", expireAfterSeconds = 7200)
	@Builder.Default
	private Date timeToLeave=new Date();
}
