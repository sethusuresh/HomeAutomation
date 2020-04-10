package com.iot.homeAutomation.User.DTO;

import java.util.List;

public class UserDeviceDTO {

	private String deviceName;
	private String deviceId;
	private List<String> roleList;

	public UserDeviceDTO(String deviceName, String deviceId, List<String> roleList) {
		super();
		this.deviceName = deviceName;
		this.deviceId = deviceId;
		this.roleList = roleList;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public List<String> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}

}
