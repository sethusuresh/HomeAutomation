package com.iot.homeAutomation.SmartGardener;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.iot.homeAutomation.Device.DeviceDTO;
import com.iot.homeAutomation.Device.DeviceManager;
import com.iot.homeAutomation.DeviceActivityAudit.DeviceAction;
import com.iot.homeAutomation.DeviceActivityAudit.DeviceActivityDTO;
import com.iot.homeAutomation.DeviceActivityAudit.DeviceActivityManager;
import com.iot.homeAutomation.User.UserManager;
import com.iot.homeAutomation.User.UserRoles;
import com.iot.homeAutomation.User.DTO.UserDTO;
import com.iot.homeAutomation.User.DTO.UserDeviceDTO;
import com.iot.homeAutomation.UserActivityAudit.UserAction;
import com.iot.homeAutomation.UserActivityAudit.UserActivityDTO;
import com.iot.homeAutomation.UserActivityAudit.UserActivityManager;

@Service
public class SmartGardenerManagerImpl implements SmartGardenerManager {

	@Resource
	DeviceManager deviceManager;
	
	@Resource
	UserManager userManager;
	
	@Resource
	DeviceActivityManager deviceActivityManager;
	
	@Resource
	UserActivityManager userActivityManager;
	
	@Override
	public String mapUserToDevice(String deviceId, String userId) throws Exception {
		String userRequestStatus = null;
		UserDTO user = userManager.findUserById(userId);
		DeviceDTO device = deviceManager.findDeviceById(deviceId);
		if(!ObjectUtils.isEmpty(device)) {
			if(!ObjectUtils.isEmpty(device.getAdminList()) && device.getAdminList().size()>0) {
				//get approval from admin
				userRequestStatus = UserRequestStatus.PENDING.toString();
			}
			else {
				addDeviceToUser(user, device);
				addUserToDeviceAdminList(user, device);
				userRequestStatus = UserRequestStatus.APPROVED.toString();
			}
		}
		return userRequestStatus;
	}

	private void addUserToDeviceAdminList(UserDTO user, DeviceDTO device) throws Exception {
		List<String> adminList = device.getAdminList();
		adminList.add(user.getUserId());
		device.setAdminList(adminList);
		deviceManager.saveDevice(device);
		DeviceActivityDTO deviceActivity = new DeviceActivityDTO(device.getId(), device.getName(), DeviceAction.ADMIN_ADDED.toString(), ZonedDateTime.now().toString(), user.getUserId());
		deviceActivityManager.saveDeviceActivity(deviceActivity );
	}

	private void addDeviceToUser(UserDTO user, DeviceDTO device) throws Exception {
		List<String> roleList = new ArrayList<>();
		roleList.add(UserRoles.ADMIN.toString());
		UserDeviceDTO userDevice = new UserDeviceDTO(device.getName(), device.getId(), roleList);
		List<UserDeviceDTO> userDeviceList = user.getUserDeviceList();
		userDeviceList.add(userDevice);
		user.setUserDeviceList(userDeviceList);
		userManager.saveUser(user);
		UserActivityDTO userActivity = new UserActivityDTO(user.getUserName(), user.getUserId(), device.getId(), UserAction.DEVICE_ADDED.toString(), ZonedDateTime.now().toString());
		userActivityManager.saveUserActivity(userActivity);
	}
}
