package com.iot.homeAutomation.Device;

import java.util.List;

import com.iot.homeAutomation.Device.DTO.DeviceDTO;
import com.iot.homeAutomation.Device.DTO.WaterConfigDTO;

public interface DeviceManager {

	public DeviceDTO addDevice() throws Exception;

	void saveDeviceName(DeviceDTO device, String userId) throws Exception;

	DeviceDTO findDeviceById(String deviceId) throws Exception;

	void saveDevice(DeviceDTO device) throws Exception;

	List<DeviceDTO> findAllDeviceByUserId(String userId) throws Exception;

	void saveWaterConfig(WaterConfigDTO waterConfig, String deviceId, String userId) throws Exception;

	boolean checkUserIsAdmin(String deviceId, String userId) throws Exception;

}
