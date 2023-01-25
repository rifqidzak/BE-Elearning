package com.lawencon.e.learning.dto.attendancequiz;

import javax.validation.constraints.NotNull;

public class AttendanceQuizInsertReqDataDto {

	@NotNull(message = "Class Not Found")
	private Long studyClassDetailId;
	
	@NotNull(message = "Schedule Not Found")
	private Long scheduleId;


	public Long getStudyClassDetailId() {
		return studyClassDetailId;
	}

	public void setStudyClassDetailId(Long studyClassDetailId) {
		this.studyClassDetailId = studyClassDetailId;
	}

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

}
