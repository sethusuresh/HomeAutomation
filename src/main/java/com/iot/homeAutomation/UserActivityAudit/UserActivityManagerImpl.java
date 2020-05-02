package com.iot.homeAutomation.UserActivityAudit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserActivityManagerImpl implements UserActivityManager {
	
	private static final Logger logger = LoggerFactory.getLogger(UserActivityManagerImpl.class);
	
	@Resource
	UserActivityRepository userActivityRepository;

	@Override
	public void saveUserActivity(UserActivityDTO userActivity) {
		try {
			userActivityRepository.saveUserActivity(userActivity);
		} catch (Exception e) {
			logger.error("Error in saveUserActivity for userId:- and Action:- {}", userActivity.getUserId(), userActivity.getAction(), e);
		}
	}

}
