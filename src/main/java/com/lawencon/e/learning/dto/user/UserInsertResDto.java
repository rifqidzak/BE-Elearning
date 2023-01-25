package com.lawencon.e.learning.dto.user;

public class UserInsertResDto {
	private UserInsertResDataDto data; 
	private String message;
	public UserInsertResDataDto getData() {
		return data;
	}
	public void setData(UserInsertResDataDto data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
