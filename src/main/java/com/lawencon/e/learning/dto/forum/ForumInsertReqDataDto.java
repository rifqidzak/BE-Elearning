package com.lawencon.e.learning.dto.forum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ForumInsertReqDataDto {
	@NotBlank(message = "Title Required!")
	@Size(max = 40, message = "Title Range Max 40")
	private String titleForum;

	@NotBlank(message = "Body Required!")
	private String bodyForum;

	@NotNull(message = "Class Not Found")
	private Long studyClassDetailId;

	public String getTitleForum() {
		return titleForum;
	}

	public void setTitleForum(String titleForum) {
		this.titleForum = titleForum;
	}

	public String getBodyForum() {
		return bodyForum;
	}

	public void setBodyForum(String bodyForum) {
		this.bodyForum = bodyForum;
	}

	public Long getStudyClassDetailId() {
		return studyClassDetailId;
	}

	public void setStudyClassDetailId(Long studyClassDetailId) {
		this.studyClassDetailId = studyClassDetailId;
	}

}
