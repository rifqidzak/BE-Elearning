package com.lawencon.e.learning.dto.studyclass;

public class StudyClassUpdateResDto {
	private StudyClassUpdateResDataDto data;
	private String message;

	public StudyClassUpdateResDataDto getData() {
		return data;
	}

	public void setData(StudyClassUpdateResDataDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
