package com.lawencon.e.learning.dto.attendanceexam;

import javax.validation.constraints.NotNull;

public class AttendanceExamUpdateReqDataDto {
	@NotNull(message = "User Not Found")
	private Long id;
	
	@NotNull(message = "Version Required")
	private Integer ver;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVer() {
		return ver;
	}

	public void setVer(Integer ver) {
		this.ver = ver;
	}
}
