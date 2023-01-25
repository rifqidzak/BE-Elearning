package com.lawencon.e.learning.dto.quiz;

public class QuizInsertResDto {
	private QuizInsertResDataDto data;
	private String message;
	public QuizInsertResDataDto getData() {
		return data;
	}
	public void setData(QuizInsertResDataDto data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
