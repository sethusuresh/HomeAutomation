package com.iot.homeAutomation.Device;

import java.util.ArrayList;
import java.util.List;

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
			throw new Exception();
		}
	}
	
	@Override
	public DeviceDTO findDeviceById(String deviceId) throws Exception {
		DeviceDTO device = new DeviceDTO();
		try {
			Query query = new Query(Criteria.where("id").is(deviceId));
			device = mongoOperations.findOne(query, DeviceDTO.class);
			System.out.println("Fetched device from DB for deviceId:- " + deviceId);
		} catch (Exception e) {
			System.out.println("Error in finding device from DB for deviceId:- " + deviceId);
			throw new Exception();
		}
		return device;
	}
	
	@Override
	public List<DeviceDTO> findDeviceByIdList(List<String> deviceIdList) throws Exception{
		List<DeviceDTO> deviceList = new ArrayList<>();
		try {
			Query query = new Query(Criteria.where("id").in(deviceIdList));
			deviceList = mongoOperations.find(query, DeviceDTO.class);
			System.out.println("Fetched devices from DB for deviceId in:- " + deviceIdList.toArray().toString());
		} catch (Exception e) {
			System.out.println("Error in fetching device from DB for deviceID in:- " + deviceIdList.toArray().toString());
			throw new Exception();
		}
		return deviceList;
	}
	
	@Override
	public void saveDevice(DeviceDTO device) throws Exception {
		try {
			mongoOperations.save(device);
			System.out.println("device saveed in DB for deviceId:- " + device.getId());
		} catch (Exception e) {
			System.out.println("Error saving device in DB for deviceId:- " + device.getId());
			throw new Exception();
		}
	}
	
	@Override
	public void saveWaterConfig(String deviceId, WaterConfigDTO waterConfig) throws Exception {
		try {
			Query query = new Query(Criteria.where("id").is(deviceId));
			Update updateQuery = new Update().set("waterConfig", waterConfig);
			mongoOperations.updateFirst(query, updateQuery, DeviceDTO.class);
			System.out.println("Water config updated for deviceId:- " + deviceId);
		} catch (Exception e) {
			System.out.println("Error updating water config for deviceId:- " + deviceId);
			throw new Exception();
		}
	}
	
}
