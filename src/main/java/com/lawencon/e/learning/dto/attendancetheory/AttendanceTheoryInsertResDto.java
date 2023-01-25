package com.lawencon.e.learning.dto.attendancetheory;

public class AttendanceTheoryInsertResDto {
	private AttendanceTheoryInsertResDataDto data;
	private String message;
	

	public AttendanceTheoryInsertResDataDto getData() {
		return data;
	}
	public void setData(AttendanceTheoryInsertResDataDto data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
