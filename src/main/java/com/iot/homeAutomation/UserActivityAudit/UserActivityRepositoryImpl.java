package com.iot.homeAutomation.UserActivityAudit;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

@Service
public class UserActivityRepositoryImpl implements UserActivityRepository {

	@Resource
	MongoOperations mongoOperations;

	@Override
	public void saveUserActivity(UserActivityDTO userActivity) {
		mongoOperations.save(userActivity);

	}

}
