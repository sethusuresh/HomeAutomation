package com.iot.homeAutomation.DeviceActivityAudit;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

@Service
public class DeviceActivityRepositoryImpl implements DeviceActivityRepository {

	@Resource
	MongoOperations mongoOperations;

	@Override
	public void saveDeviceActivity(DeviceActivityDTO deviceActivity) throws Exception {
		try {
			mongoOperations.save(deviceActivity);
			System.out.println("Device activity saved for deviceId:- " + deviceActivity.getDeviceId() + " and action:- " + deviceActivity.getAction());
		} catch (Exception e) {
			System.out.println("Error in saving device activity for deivceId:- " + deviceActivity.getDeviceId() + " and action:- " + deviceActivity.getAction());
			throw new Exception();
		}
	}

}
