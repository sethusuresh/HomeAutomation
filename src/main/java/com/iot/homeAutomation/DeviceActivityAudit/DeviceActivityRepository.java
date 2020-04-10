package com.iot.homeAutomation.DeviceActivityAudit;

public interface DeviceActivityRepository {

	public void saveDeviceActivity(DeviceActivityDTO deviceActivity) throws Exception;
}
