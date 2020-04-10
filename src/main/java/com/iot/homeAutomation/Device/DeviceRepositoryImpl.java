package com.iot.homeAutomation.Device;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class DeviceRepositoryImpl implements DeviceRepository {

	@Resource
	MongoOperations mongoOperations;

	@Override
	public DeviceDTO addDevice() throws Exception {
		DeviceDTO device = new DeviceDTO();
		try {
			device = mongoOperations.save(new DeviceDTO());
			System.out.println("Device saved in DB with Id:- " + device.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return device;
	}
	
	@Override
	public void saveDeviceName(DeviceDTO device) throws Exception {
		try {
			Query query = new Query(Criteria.where("id").is(device.getId()));
			Update updateQuery = new Update().set("name", device.getName());
			mongoOperations.updateFirst(query, updateQuery, DeviceDTO.class);
			System.out.println("Device name updated to:- " + device.getName() + " for deviceId:- " + device.getId());
		} catch (Exception e) {
			System.out.println("Device name update failed for deviceId:- " + device.getId());
			e.printStackTrace();
			throw new Exception();
		}
	}

}
