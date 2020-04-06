package com.iot.homeAutomation.UserActivityAudit;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iot.homeAutomation.Util.Response;

@RestController
public class UserActivityController {
	
	@Resource
	UserActivityManager userActivityManager;

	@RequestMapping(value = "/logUserActivity", method = RequestMethod.POST)
	public Response<String> logUserActivity(@RequestBody UserActivityDTO request) {
		userActivityManager.saveUserActivity(request);
		Response<String> reponse = new Response<String>("200", "Success", null);
		return reponse;
	}
}
