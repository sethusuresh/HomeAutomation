package com.iot.homeAutomation.UserActivityAudit;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_activity")
public class UserActivityDTO {

	@Id
	private String id;
	private String userName;
	private String userId;
	private String targetDevice;
	private String action;
	private String time;

	public UserActivityDTO(String userName, String userId, String targetDevice, String action, String time) {
		super();
		this.userName = userName;
		this.userId = userId;
		this.targetDevice = targetDevice;
		this.action = action;
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTargetDevice() {
		return targetDevice;
	}

	public void setTargetDevice(String targetDevice) {
		this.targetDevice = targetDevice;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
