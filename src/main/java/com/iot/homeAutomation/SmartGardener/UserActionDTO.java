package com.iot.homeAutomation.SmartGardener;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.iot.homeAutomation.UserActivityAudit.UserActivityDTO;

@Document(collection="user_action")
public class UserActionDTO {

	@Id
	private String id;
	private UserActivityDTO userActivity;
	private DeviceDTO device;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public UserActivityDTO getUserActivity() {
		return userActivity;
	}
	public void setUserActivity(UserActivityDTO userActivity) {
		this.userActivity = userActivity;
	}
	public DeviceDTO getDevice() {
		return device;
	}
	public void setDevice(DeviceDTO device) {
		this.device = device;
	}
	
}
