package com.lawencon.e.learning.dto.studyclassdetail;

import javax.validation.constraints.NotNull;

public class StudyClassDetailInsertReqDataDto {
	@NotNull(message = "Class Required !")
	private Long studyClassId;

	public Long getStudyClassId() {
		return studyClassId;
	}

	public void setStudyClassId(Long studyClassId) {
		this.studyClassId = studyClassId;
	}

}
