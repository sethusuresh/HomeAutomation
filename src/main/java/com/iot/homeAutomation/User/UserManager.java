package com.iot.homeAutomation.User;

import java.util.List;

import com.iot.homeAutomation.User.DTO.UserDTO;

public interface UserManager {
//test
	public boolean checkAndAddUser(UserDTO user) throws Exception;

	UserDTO findUserById(String userId) throws Exception;

	void saveUser(UserDTO user) throws Exception;

	List<String> findDeviceIdListByUserId(String userId) throws Exception;

}
