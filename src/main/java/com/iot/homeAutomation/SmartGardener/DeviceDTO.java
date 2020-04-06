package com.iot.homeAutomation.SmartGardener;

import java.util.List;

public class DeviceDTO {

	private String name;
	private String id;
	private WaterConfigDTO waterConfig;
	private List<String> adminList;

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

}
