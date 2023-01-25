package com.lawencon.e.learning.dto.exam;

public class ExamInsertResDto {
	private ExamInsertResDataDto data;
	private String message;
	public ExamInsertResDataDto getData() {
		return data;
	}
	public void setData(ExamInsertResDataDto data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
