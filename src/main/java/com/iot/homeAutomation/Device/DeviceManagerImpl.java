package com.iot.homeAutomation.Device;

import java.time.ZonedDateTime;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iot.homeAutomation.DeviceActivityAudit.DeviceAction;
import com.iot.homeAutomation.DeviceActivityAudit.DeviceActivityDTO;
import com.iot.homeAutomation.DeviceActivityAudit.DeviceActivityManager;

@Service
public class DeviceManagerImpl implements DeviceManager {

	@Resource
	DeviceRepository deviceRepository;

	@Resource
	DeviceActivityManager deviceActivityManager;

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
			throw new Exception();
		}
	}

}
