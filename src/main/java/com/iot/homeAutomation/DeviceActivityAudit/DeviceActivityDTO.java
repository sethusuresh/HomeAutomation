package com.iot.homeAutomation.DeviceActivityAudit;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "device_activity")
public class DeviceActivityDTO {

	@Id
	private String id;
	private String deviceId;
	private String deviceName;
	private String action;
	private String time;
	private String triggeredBy;

	public DeviceActivityDTO(String deviceId, String deviceName, String action, String time, String triggeredBy) {
		super();
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		this.action = action;
		this.time = time;
		this.triggeredBy = triggeredBy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
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

	public String getTriggeredBy() {
		return triggeredBy;
	}

	public void setTriggeredBy(String triggeredBy) {
		this.triggeredBy = triggeredBy;
	}

}
