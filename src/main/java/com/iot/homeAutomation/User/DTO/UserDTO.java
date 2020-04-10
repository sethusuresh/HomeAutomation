package com.iot.homeAutomation.User.DTO;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class UserDTO {

	@Id
	private String id;
	@Indexed
	private String userId;
	private String userName;
	private List<UserDeviceDTO> userDeviceList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<UserDeviceDTO> getUserDeviceList() {
		return userDeviceList;
	}

	public void setUserDeviceList(List<UserDeviceDTO> rolesList) {
		this.userDeviceList = rolesList;
	}

}
