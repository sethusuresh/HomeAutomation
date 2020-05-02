package com.iot.homeAutomation.User;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.iot.homeAutomation.User.DTO.UserDTO;
import com.iot.homeAutomation.User.DTO.UserDeviceDTO;
import com.iot.homeAutomation.UserActivityAudit.UserActivityDTO;
import com.iot.homeAutomation.UserActivityAudit.UserActivityManager;

@Service
public class UserManagerImpl implements UserManager {

	private static final Logger logger = LoggerFactory.getLogger(UserManagerImpl.class);
	@Resource
	UserRepository userRepository;
	
	@Resource
	UserActivityManager userActivityManager;

	@Override
	public boolean checkAndAddUser(UserDTO user) throws Exception {
		boolean isUserAdded = userRepository.checkAndAddUser(user);
		UserActivityDTO userActivity = new UserActivityDTO(user.getUserName(), user.getUserId(), null, "", ZonedDateTime.now().toString());
		if(isUserAdded) {
			userActivity.setAction("SIGNUP");
		}
		else {
			userActivity.setAction("LOGIN");
		}
		userActivityManager.saveUserActivity(userActivity);
		return isUserAdded;
	}
	
	@Override
	public UserDTO findUserById(String userId) throws Exception {
		UserDTO user = new UserDTO();
		try {
			user = userRepository.findUserById(userId);
		} catch (Exception e) {
			logger.error("Error in findUserById for userId:- {}", userId, e);
			throw new Exception();
		}
		return user;
	}
	
	@Override
	public void saveUser(UserDTO user) throws Exception {
		try {
			userRepository.saveUser(user);
		} catch (Exception e) {
			logger.error("Error in saveUser for userId:- {}", user.getUserId(), e);
			throw new Exception();
		}
	}
	
	@Override
	public List<String> findDeviceIdListByUserId(String userId) throws Exception{
		UserDTO user = findUserById(userId);
		List<String> deviceIdList = user.getUserDeviceList().stream().map(UserDeviceDTO::getDeviceId).collect(Collectors.toList());
		return deviceIdList;
	}

}
