package com.iot.homeAutomation.Device;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.iot.homeAutomation.Device.DTO.DeviceDTO;
import com.iot.homeAutomation.Device.DTO.WaterConfigDTO;

@Service
public class DeviceRepositoryImpl implements DeviceRepository {

	private static final Logger logger = LoggerFactory.getLogger(DeviceRepositoryImpl.class);
	
	@Resource
	MongoOperations mongoOperations;

	@Override
	public DeviceDTO addDevice() throws Exception {
		DeviceDTO device = new DeviceDTO();
		try {
			device = mongoOperations.save(new DeviceDTO());
			logger.debug("Device saved in DB with Id:- {}", device.getId());
		} catch (Exception e) {
			logger.error("Error in addDevice:- {}", e);
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
			logger.debug("Device name updated to:- {} for deviceId:- {}", device.getName(), device.getId());
		} catch (Exception e) {
			logger.debug("Device name update failed for deviceId:- {}", device.getId());
			throw new Exception();
		}
	}
	
	@Override
	public DeviceDTO findDeviceById(String deviceId) throws Exception {
		DeviceDTO device = new DeviceDTO();
		try {
			Query query = new Query(Criteria.where("id").is(deviceId));
			device = mongoOperations.findOne(query, DeviceDTO.class);
			logger.debug("Fetched device from DB for deviceId:- {}", deviceId);
		} catch (Exception e) {
			logger.debug("Error in finding device from DB for deviceId:- {}", deviceId);
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
			logger.debug("Fetched devices from DB for deviceId in:- {}", deviceIdList.toArray().toString());
		} catch (Exception e) {
			logger.debug("Error in fetching device from DB for deviceID in:- {}", deviceIdList.toArray().toString());
			throw new Exception();
		}
		return deviceList;
	}
	
	@Override
	public void saveDevice(DeviceDTO device) throws Exception {
		try {
			mongoOperations.save(device);
			logger.debug("Device saved in DB for deviceId:- {}", device.getId());
		} catch (Exception e) {
			logger.debug("Error saving device in DB for deviceId:- {}", device.getId());
			throw new Exception();
		}
	}
	
	@Override
	public void saveWaterConfig(String deviceId, WaterConfigDTO waterConfig) throws Exception {
		try {
			Query query = new Query(Criteria.where("id").is(deviceId));
			Update updateQuery = new Update().set("waterConfig", waterConfig);
			mongoOperations.updateFirst(query, updateQuery, DeviceDTO.class);
			logger.debug("Water config updated for deviceId:- {}", deviceId);
		} catch (Exception e) {
			logger.debug("Error updating water config for deviceId:- {}", deviceId);
			throw new Exception();
		}
	}
	
}
