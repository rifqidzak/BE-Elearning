package com.lawencon.e.learning.dto.attendancetheory;

public class AttendanceTheoryUpdateResDto {
	private AttendanceTheoryUpdateResDataDto data;
	private String message;

	public AttendanceTheoryUpdateResDataDto getData() {
		return data;
	}

	public void setData(AttendanceTheoryUpdateResDataDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
