package com.lawencon.e.learning.dto.attendancequiz;

public class AttendanceQuizInsertResDto {
	private AttendanceQuizInsertResDataDto data;
	private String message;

	public AttendanceQuizInsertResDataDto getData() {
		return data;
	}

	public void setData(AttendanceQuizInsertResDataDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
