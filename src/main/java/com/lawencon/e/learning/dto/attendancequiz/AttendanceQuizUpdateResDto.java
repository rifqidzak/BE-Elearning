package com.lawencon.e.learning.dto.attendancequiz;

public class AttendanceQuizUpdateResDto {
	private AttendanceQuizUpdateResDataDto data;
	private String message;

	public AttendanceQuizUpdateResDataDto getData() {
		return data;
	}

	public void setData(AttendanceQuizUpdateResDataDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
