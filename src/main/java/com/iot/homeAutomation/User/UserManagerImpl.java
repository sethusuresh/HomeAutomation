package com.iot.homeAutomation.User;

import java.time.ZonedDateTime;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iot.homeAutomation.User.DTO.UserDTO;
import com.iot.homeAutomation.UserActivityAudit.UserActivityDTO;
import com.iot.homeAutomation.UserActivityAudit.UserActivityManager;

@Service
public class UserManagerImpl implements UserManager {

	@Resource
	UserRepository userRepository;
	
	@Resource
	UserActivityManager userActivityManager;

	@Override
	public boolean checkAndAddUser(UserDTO user) throws Exception {
		boolean isUserAdded = userRepository.checkAndAddUser(user);
		UserActivityDTO userActivity = new UserActivityDTO(user.getUserName(), user.getUserId(), null, "LOGIN", ZonedDateTime.now().toString());
		userActivityManager.saveUserActivity(userActivity );
		return isUserAdded;
	}

}
