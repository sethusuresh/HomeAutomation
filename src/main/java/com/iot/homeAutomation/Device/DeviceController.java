package com.iot.homeAutomation.Device;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iot.homeAutomation.Util.Response;

@RestController
public class DeviceController {
	
	@Resource
	DeviceManager deviceManager;

	@RequestMapping(value="/addDevice", method=RequestMethod.POST)
	public Response<DeviceDTO> addDevice() {
		Response<DeviceDTO> reponse;
		try {
			DeviceDTO device = deviceManager.addDevice();
			reponse = new Response<DeviceDTO>("200", "Success", device);
		} catch (Exception e) {
			reponse = new Response<DeviceDTO>("500", "Failed", null);
			e.printStackTrace();
		}
		return reponse;
	}

	@RequestMapping(value="/saveDeviceName", method=RequestMethod.POST)
	public Response<String> addDevice(@RequestBody DeviceDTO device, @RequestHeader String userId) {
		Response<String> reponse;
		try {
			deviceManager.saveDeviceName(device, userId);
			reponse = new Response<String>("200", "Success", device.getName());
		} catch (Exception e) {
			reponse = new Response<String>("500", "Failed", null);
			e.printStackTrace();
		}
		return reponse;
	}
}
