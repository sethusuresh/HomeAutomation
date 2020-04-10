package com.iot.homeAutomation.DeviceActivityAudit;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class DeviceActivityManagerImpl implements DeviceActivityManager {

	@Resource
	DeviceActivityRepository deviceActivityRepository;
	
	@Override
	public void saveDeviceActivity(DeviceActivityDTO deviceActivity) {
		try {
			deviceActivityRepository.saveDeviceActivity(deviceActivity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
