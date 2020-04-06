package com.iot.homeAutomation.User;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iot.homeAutomation.User.DTO.UserDTO;
import com.iot.homeAutomation.Util.Response;

@RestController
public class UserController {

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
			e.printStackTrace();
			reponse = new Response<Boolean>("500", "Failed", null);
		}

		return reponse;
	}
}
