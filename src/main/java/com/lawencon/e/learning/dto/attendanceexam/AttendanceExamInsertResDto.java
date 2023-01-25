package com.lawencon.e.learning.dto.attendanceexam;

public class AttendanceExamInsertResDto {
	private AttendanceExamInsertResDataDto data;
	private String message;
	

	public AttendanceExamInsertResDataDto getData() {
		return data;
	}
	public void setData(AttendanceExamInsertResDataDto data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
