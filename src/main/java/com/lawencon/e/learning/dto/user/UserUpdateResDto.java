package com.lawencon.e.learning.dto.user;

public class UserUpdateResDto {
	private UserUpdateResDataDto data;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserUpdateResDataDto getData() {
		return data;
	}

	public void setData(UserUpdateResDataDto data) {
		this.data = data;
	}
}
