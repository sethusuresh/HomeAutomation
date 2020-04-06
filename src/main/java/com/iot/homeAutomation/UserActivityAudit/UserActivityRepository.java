package com.iot.homeAutomation.UserActivityAudit;

public interface UserActivityRepository {
	
	public void saveUserActivity(UserActivityDTO userActivity);
}
