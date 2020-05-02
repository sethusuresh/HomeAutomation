package com.iot.homeAutomation.UserActivityAudit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

@Service
public class UserActivityRepositoryImpl implements UserActivityRepository {

	private static final Logger logger = LoggerFactory.getLogger(UserActivityRepositoryImpl.class);
	
	@Resource
	MongoOperations mongoOperations;

	@Override
	public void saveUserActivity(UserActivityDTO userActivity) throws Exception {
		try {
			mongoOperations.save(userActivity);
			logger.debug("User activity saved in DB for userId:- {}", userActivity.getUserId());
		} catch (Exception e) {
			logger.debug("Error saving user activity in DB for userId:- {}", userActivity.getUserId());
			throw new Exception();
		}
	}

}
