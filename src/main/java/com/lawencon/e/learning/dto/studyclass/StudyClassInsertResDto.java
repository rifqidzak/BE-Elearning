package com.lawencon.e.learning.dto.studyclass;

public class StudyClassInsertResDto {
	private StudyClassInsertResDataDto data;
	private String message;

	public StudyClassInsertResDataDto getData() {
		return data;
	}

	public void setData(StudyClassInsertResDataDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
