package com.iot.homeAutomation.Device;

import java.util.List;

public interface DeviceRepository {

	public DeviceDTO addDevice() throws Exception;

	void saveDeviceName(DeviceDTO device) throws Exception;

	DeviceDTO findDeviceById(String deviceId) throws Exception;

	void saveDevice(DeviceDTO device) throws Exception;

	List<DeviceDTO> findDeviceByIdList(List<String> deviceIdList) throws Exception;
}
