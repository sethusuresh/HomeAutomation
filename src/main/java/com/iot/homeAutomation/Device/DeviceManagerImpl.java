package com.iot.homeAutomation.Device;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.iot.homeAutomation.Device.DTO.DeviceDTO;
import com.iot.homeAutomation.Device.DTO.WaterConfigDTO;
import com.iot.homeAutomation.DeviceActivityAudit.DeviceAction;
import com.iot.homeAutomation.DeviceActivityAudit.DeviceActivityDTO;
import com.iot.homeAutomation.DeviceActivityAudit.DeviceActivityManager;
import com.iot.homeAutomation.User.UserManager;
import com.iot.homeAutomation.UserActivityAudit.UserAction;
import com.iot.homeAutomation.UserActivityAudit.UserActivityDTO;
import com.iot.homeAutomation.UserActivityAudit.UserActivityManager;

@Service
public class DeviceManagerImpl implements DeviceManager {

	@Resource
	DeviceRepository deviceRepository;

	@Resource
	DeviceActivityManager deviceActivityManager;
	
	@Resource
	UserManager userManager;
	
	@Resource
	UserActivityManager userActivityManager;

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
	
	@Override
	public void saveWaterConfig(WaterConfigDTO waterConfig, String deviceId, String userId) throws Exception {
		boolean isAdmin = checkUserIsAdmin(deviceId, userId);
		try {
			if(isAdmin) {
				deviceRepository.saveWaterConfig(deviceId, waterConfig);
				DeviceActivityDTO deviceActivity = new DeviceActivityDTO(deviceId, deviceId, DeviceAction.WATER_CONFIG_EDITED.toString(), ZonedDateTime.now().toString(), userId);
				deviceActivityManager.saveDeviceActivity(deviceActivity);
				UserActivityDTO userActivity = new UserActivityDTO(userId, userId, deviceId, UserAction.WATER_CONFIG_EDITED.toString(), ZonedDateTime.now().toString());
				userActivityManager.saveUserActivity(userActivity );
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	private boolean checkUserIsAdmin(String deviceId, String userId) throws Exception {
		boolean isAdmin = false;
		DeviceDTO device;
		try {
			device = deviceRepository.findDeviceById(deviceId);
			if(!ObjectUtils.isEmpty(device) && !ObjectUtils.isEmpty(device.getAdminList())) {
				isAdmin = device.getAdminList().contains(userId);
			}
			System.out.println("User with userID:- " + userId + " has Admin:- " + isAdmin + " for deviceId:- " + deviceId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return isAdmin;
	}

}
