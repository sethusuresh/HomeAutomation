package com.iot.homeAutomation.User;

import com.iot.homeAutomation.User.DTO.UserDTO;

public interface UserManager {

	public boolean checkAndAddUser(UserDTO user) throws Exception;

	UserDTO getUserForUserId(String userId) throws Exception;

}
