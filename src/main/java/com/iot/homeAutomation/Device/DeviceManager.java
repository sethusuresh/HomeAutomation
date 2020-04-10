package com.iot.homeAutomation.Device;

import java.util.List;

public interface DeviceManager {

	public DeviceDTO addDevice() throws Exception;

	void saveDeviceName(DeviceDTO device, String userId) throws Exception;

	DeviceDTO findDeviceById(String deviceId) throws Exception;

	void saveDevice(DeviceDTO device) throws Exception;

	List<DeviceDTO> findAllDeviceByUserId(String userId) throws Exception;

}
