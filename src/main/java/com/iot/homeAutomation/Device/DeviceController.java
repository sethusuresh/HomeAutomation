package com.iot.homeAutomation.Device;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iot.homeAutomation.Device.DTO.DeviceDTO;
import com.iot.homeAutomation.Device.DTO.WaterConfigDTO;
import com.iot.homeAutomation.Util.Response;

@RestController
public class DeviceController {
	
	@Resource
	DeviceManager deviceManager;

	@RequestMapping(value="/addDevice", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<DeviceDTO> addDevice() {
		Response<DeviceDTO> response;
		try {
			DeviceDTO device = deviceManager.addDevice();
			response = new Response<DeviceDTO>("200", "Success", device);
		} catch (Exception e) {
			response = new Response<DeviceDTO>("500", "Failed", null);
			e.printStackTrace();
		}
		return response;
	}

	@RequestMapping(value="/saveDeviceName", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> saveDeviceName(@RequestBody DeviceDTO device, @RequestHeader String userId) {
		Response<String> response;
		try {
			deviceManager.saveDeviceName(device, userId);
			response = new Response<String>("200", "Success", device.getName());
		} catch (Exception e) {
			response = new Response<String>("500", "Failed", null);
		}
		return response;
	}

	@RequestMapping(value="/{deviceId}/saveWaterConfig", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Boolean> saveWaterConfig(@RequestBody WaterConfigDTO waterConfig, @RequestHeader String deviceId, @RequestHeader String userId) {
		Response<Boolean> response;
		try {
			deviceManager.saveWaterConfig(waterConfig, deviceId, userId);
			response = new Response<Boolean>("200", "Success", true);
		} catch (Exception e) {
			response = new Response<Boolean>("500", "Failed", false);
		}
		return response;
	}
	
	@RequestMapping(value = "/getAllDevices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<List<DeviceDTO>> findAllDeviceByUserId(@RequestHeader String userId) {
		Response<List<DeviceDTO>> response;
		List<DeviceDTO> deviceList = new ArrayList<>();
		try {
			deviceList = deviceManager.findAllDeviceByUserId(userId);
			response = new Response<List<DeviceDTO>>("200", "Success", deviceList);
		} catch (Exception e) {
			response = new Response<List<DeviceDTO>>("500", "Failed", deviceList);
		}
		return response;
	}
}
