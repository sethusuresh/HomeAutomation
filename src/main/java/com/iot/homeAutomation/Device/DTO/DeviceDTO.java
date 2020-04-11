package com.iot.homeAutomation.Device.DTO;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "device")
public class DeviceDTO {

	@Id
	private String id;
	private String name;
	private WaterConfigDTO waterConfig;
	private List<String> adminList;
	private List<String> nonAdminList;
	private boolean status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public WaterConfigDTO getWaterConfig() {
		return waterConfig;
	}

	public void setWaterConfig(WaterConfigDTO waterConfig) {
		this.waterConfig = waterConfig;
	}

	public List<String> getAdminList() {
		return adminList;
	}

	public void setAdminList(List<String> adminList) {
		this.adminList = adminList;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<String> getNonAdminList() {
		return nonAdminList;
	}

	public void setNonAdminList(List<String> nonAdminList) {
		this.nonAdminList = nonAdminList;
	}

}
