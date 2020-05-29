package com.ooha.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum AppCoreConstants {

	/**URL Paths */
	PATH_LOGIN("/login"),
	
	PATH_LOGOUT("/logout");

	String stringValue;
}
