package com.ooha.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ooha.dao.HospitalRepository;
import com.ooha.dao.UserRepository;
import com.ooha.mongo.model.HospitalModel;
import com.ooha.mongo.model.UserModel;
import com.ooha.utils.CommonUtill;

@Service
public class EledgerServiceImpl implements EledgerServices {

	@Autowired
	private HospitalRepository hospitalRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void createHospital(HospitalModel hospitalModel) {
		Date currentDate = CommonUtill.getCurrentUTCTime();
		hospitalModel.setId(CommonUtill.getObjectId());
		hospitalModel.setCreatedDate(currentDate.toString());
		hospitalModel.setUpdateDate(currentDate.toString());
		hospitalModel.setIsHospitalActive(true);
		hospitalRepository.save(hospitalModel);
	}

	@Override
	public void createUser(UserModel userModel) {
		Date currentDate = CommonUtill.getCurrentUTCTime();
		userModel.setId(CommonUtill.getObjectId());
		userModel.setCreatedDate(currentDate.toString());
		userModel.setUpdateDate(currentDate.toString());
		userModel.setIsUserActive(true);
		userRepository.save(userModel);
	}

}
