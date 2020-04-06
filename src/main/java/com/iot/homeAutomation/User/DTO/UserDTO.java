package com.iot.homeAutomation.User.DTO;

import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class UserDTO {

	@Indexed
	private String userId;
	private String userName;
	private List<RoleDTO> rolesList;

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

	public List<RoleDTO> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<RoleDTO> rolesList) {
		this.rolesList = rolesList;
	}

}
