package com.lawencon.e.learning.dto.forum;

public class ForumInsertResDto {
	private ForumInsertResDataDto data;
	private String message;

	public ForumInsertResDataDto getData() {
		return data;
	}

	public void setData(ForumInsertResDataDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
