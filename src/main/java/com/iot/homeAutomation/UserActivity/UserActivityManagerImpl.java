package com.iot.homeAutomation.UserActivity;

import org.springframework.stereotype.Service;

@Service
public class UserActivityManagerImpl implements UserActivityManager {

	@Override
	public void saveUserActivity(UserActivityDTO userActivity) {
		//save userActivity DTO in db
	}

}
