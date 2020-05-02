package com.iot.homeAutomation.DeviceActivityAudit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

@Service
public class DeviceActivityRepositoryImpl implements DeviceActivityRepository {

	private static final Logger logger = LoggerFactory.getLogger(DeviceActivityRepositoryImpl.class);
	
	@Resource
	MongoOperations mongoOperations;

	@Override
	public void saveDeviceActivity(DeviceActivityDTO deviceActivity) throws Exception {
		try {
			mongoOperations.save(deviceActivity);
			logger.debug("Device activity saved for deviceId:- {} and action:- {}", deviceActivity.getDeviceId(), deviceActivity.getAction());
		} catch (Exception e) {
			logger.debug("Error in saving device activity for deivceId:-  {} and action:- {}", deviceActivity.getDeviceId(), deviceActivity.getAction());
			throw new Exception();
		}
	}

}
