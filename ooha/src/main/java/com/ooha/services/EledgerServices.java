package com.ooha.services;

import com.ooha.mongo.model.HospitalModel;
import com.ooha.mongo.model.UserModel;

public interface EledgerServices {

	void createHospital(HospitalModel hospitalModel);
	
	void createUser(UserModel userModel);
	
	
}
