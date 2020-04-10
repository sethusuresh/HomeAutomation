package com.iot.homeAutomation.Device;

public interface DeviceManager {

	public DeviceDTO addDevice() throws Exception;

	void saveDeviceName(DeviceDTO device, String userId) throws Exception;

}
