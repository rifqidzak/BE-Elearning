package com.lawencon.e.learning.dto.scheduleexam;

import java.util.List;

public class ScheduleExamsDto {
	private List<ScheduleExamDataDto> data;
	public List<ScheduleExamDataDto> getData() {
		return data;
	}

	public void setData(List<ScheduleExamDataDto> data) {
		this.data = data;
	}
}
