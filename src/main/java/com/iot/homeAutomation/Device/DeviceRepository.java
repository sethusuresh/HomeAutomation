package com.iot.homeAutomation.Device;

import java.util.List;

import com.iot.homeAutomation.Device.DTO.DeviceDTO;
import com.iot.homeAutomation.Device.DTO.WaterConfigDTO;

public interface DeviceRepository {

	public DeviceDTO addDevice() throws Exception;

	void saveDeviceName(DeviceDTO device) throws Exception;

	DeviceDTO findDeviceById(String deviceId) throws Exception;

	void saveDevice(DeviceDTO device) throws Exception;

	List<DeviceDTO> findDeviceByIdList(List<String> deviceIdList) throws Exception;

	void saveWaterConfig(String deviceId, WaterConfigDTO waterConfig) throws Exception;
}
