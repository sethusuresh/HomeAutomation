package com.iot.homeAutomation.Device;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iot.homeAutomation.DeviceActivityAudit.DeviceAction;
import com.iot.homeAutomation.DeviceActivityAudit.DeviceActivityDTO;
import com.iot.homeAutomation.DeviceActivityAudit.DeviceActivityManager;
import com.iot.homeAutomation.User.UserManager;

@Service
public class DeviceManagerImpl implements DeviceManager {

	@Resource
	DeviceRepository deviceRepository;

	@Resource
	DeviceActivityManager deviceActivityManager;
	
	@Resource
	UserManager userManager;

	@Override
	public DeviceDTO addDevice() throws Exception {
		DeviceDTO device = new DeviceDTO();
		try {
			device = deviceRepository.addDevice();
			DeviceActivityDTO deviceActivity = new DeviceActivityDTO(device.getId(), null, "DEVICE_CREATED", ZonedDateTime.now().toString(), null);
			deviceActivityManager.saveDeviceActivity(deviceActivity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return device;
	}
	
	@Override
	public void saveDeviceName(DeviceDTO device, String userId) throws Exception {
		try {
			deviceRepository.saveDeviceName(device);
			DeviceActivityDTO deviceActivity = new DeviceActivityDTO(device.getId(), device.getName(), DeviceAction.NAME_EDITED.toString(), ZonedDateTime.now().toString(), userId);
			deviceActivityManager.saveDeviceActivity(deviceActivity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	@Override
	public DeviceDTO findDeviceById(String deviceId) throws Exception {
		DeviceDTO device = new DeviceDTO();
		try {
			device = deviceRepository.findDeviceById(deviceId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return device;
	}
	
	@Override
	public List<DeviceDTO> findAllDeviceByUserId(String userId) throws Exception{
		List<DeviceDTO> deviceList = new ArrayList<>();
		try {
			List<String> deviceIdList = userManager.findDeviceIdListByUserId(userId);
			deviceList = deviceRepository.findDeviceByIdList(deviceIdList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return deviceList;
		
	}
	
	@Override
	public void saveDevice(DeviceDTO device) throws Exception {
		try {
			deviceRepository.saveDevice(device);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

}
