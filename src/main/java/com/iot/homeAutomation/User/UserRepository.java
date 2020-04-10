package com.iot.homeAutomation.User;

import com.iot.homeAutomation.User.DTO.UserDTO;

public interface UserRepository {

	public boolean checkAndAddUser(UserDTO user) throws Exception;

	UserDTO findUserById(String userId) throws Exception;

	void saveUser(UserDTO user) throws Exception;
}
