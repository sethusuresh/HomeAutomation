package com.iot.homeAutomation.UserActivityAudit;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class UserActivityManagerImpl implements UserActivityManager {
	
	@Resource
	UserActivityRepository userActivityRepository;

	@Override
	public void saveUserActivity(UserActivityDTO userActivity) {
		userActivityRepository.saveUserActivity(userActivity);
	}

}
