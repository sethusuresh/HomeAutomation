package com.iot.homeAutomation.SmartGardener;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iot.homeAutomation.Util.Response;

@RestController
public class SmartGardenerController {
	
	@Resource
	SmartGardenerManager smartGardenerManager;

	@RequestMapping(value = "/{deviceId}/mapUserToDevice", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> mapUserToDevice(@PathVariable String deviceId, @RequestHeader String userId) {
		Response<String> response;
		try {
			String userRequestStatus = smartGardenerManager.mapUserToDevice(deviceId, userId);
			response = new Response<String>("200", "Success", userRequestStatus);
		} catch (Exception e) {
			response = new Response<String>("500", "Failed", UserRequestStatus.FAILED.toString());
		}
		return response;
	}
	
	@RequestMapping(value = "/{deviceId}/waterNow", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Boolean> waterNow(@PathVariable String deviceId, @RequestHeader String userId) {
		Response<Boolean> response;
		try {
			boolean isWateringStarted = smartGardenerManager.startWatering(deviceId, userId);
			response = new Response<Boolean>("200", "Success", isWateringStarted);
		} catch (Exception e) {
			response = new Response<Boolean>("500", "Failed", false);
		}
		return response;
	}
	
}
