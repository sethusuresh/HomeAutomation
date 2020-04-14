package com.iot.homeAutomation.SmartGardener;

import com.iot.homeAutomation.Device.DTO.WaterConfigDTO;

public class SmartGardenerRequestDTO {

	private String deviceId;
	private String userId;
	private String action;
	private WaterConfigDTO waterConfig;
	
	public SmartGardenerRequestDTO(String deviceId, String userId, String action, WaterConfigDTO waterConfig) {
		super();
		this.deviceId = deviceId;
		this.userId = userId;
		this.action = action;
		this.waterConfig = waterConfig;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public WaterConfigDTO getWaterConfig() {
		return waterConfig;
	}
	public void setWaterConfig(WaterConfigDTO waterConfig) {
		this.waterConfig = waterConfig;
	}
	
}
