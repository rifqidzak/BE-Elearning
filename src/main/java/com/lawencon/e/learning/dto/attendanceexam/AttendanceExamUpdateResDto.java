package com.lawencon.e.learning.dto.attendanceexam;

public class AttendanceExamUpdateResDto {
	private AttendanceExamUpdateResDataDto data;
	private String message;

	public AttendanceExamUpdateResDataDto getData() {
		return data;
	}

	public void setData(AttendanceExamUpdateResDataDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
