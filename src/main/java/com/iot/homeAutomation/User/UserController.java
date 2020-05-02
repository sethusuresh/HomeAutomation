package com.iot.homeAutomation.User;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iot.homeAutomation.User.DTO.UserDTO;
import com.iot.homeAutomation.Util.Response;

@RestController
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource
	UserManager userManager;

	@RequestMapping(value = "/checkAndAddUser", method = RequestMethod.POST)
	public Response<Boolean> checkAndAddUser(@RequestBody UserDTO user) {
		boolean isUserAdded;
		Response<Boolean> reponse;
		try {
			isUserAdded = userManager.checkAndAddUser(user);
			reponse = new Response<Boolean>("200", "Success", isUserAdded);
		} catch (Exception e) {
			logger.error("Error in checkAndAddUser for userId:- {}", user.getUserId(), e);
			reponse = new Response<Boolean>("500", "Failed", null);
		}

		return reponse;
	}
}
