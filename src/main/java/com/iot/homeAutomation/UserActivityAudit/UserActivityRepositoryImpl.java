package com.iot.homeAutomation.UserActivityAudit;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

@Service
public class UserActivityRepositoryImpl implements UserActivityRepository {

	@Resource
	MongoOperations mongoOperations;

	@Override
	public void saveUserActivity(UserActivityDTO userActivity) throws Exception {
		try {
			mongoOperations.save(userActivity);
			System.out.println("User activity saved in DB for userId:- " + userActivity.getUserId());
		} catch (Exception e) {
			System.out.println("Error saving user activity in DB for userId:- " + userActivity.getUserId());
			throw new Exception();
		}
	}

}
