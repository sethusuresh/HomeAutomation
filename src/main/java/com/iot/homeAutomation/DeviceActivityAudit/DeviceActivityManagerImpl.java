package com.iot.homeAutomation.DeviceActivityAudit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DeviceActivityManagerImpl implements DeviceActivityManager {

	private static final Logger logger = LoggerFactory.getLogger(DeviceActivityManagerImpl.class);
	@Resource
	DeviceActivityRepository deviceActivityRepository;
	
	@Override
	public void saveDeviceActivity(DeviceActivityDTO deviceActivity) {
		try {
			deviceActivityRepository.saveDeviceActivity(deviceActivity);
		} catch (Exception e) {
			logger.error("Error in saveDeviceActivity for deviceId:- {} and Action:- {}", deviceActivity.getDeviceId(), deviceActivity.getAction(), e);
		}
	}

}
