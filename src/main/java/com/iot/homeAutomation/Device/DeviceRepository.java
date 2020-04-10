package com.iot.homeAutomation.Device;

public interface DeviceRepository {

	public DeviceDTO addDevice() throws Exception;

	void saveDeviceName(DeviceDTO device) throws Exception;
}
