package com.lawencon.e.learning.dto.theory;

public class TheoryInsertResDto {
	private TheoryInsertResDataDto data;
	private String message;
	public TheoryInsertResDataDto getData() {
		return data;
	}
	public void setData(TheoryInsertResDataDto data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
