package com.lawencon.e.learning.dto.forum;

import java.time.LocalDateTime;

public class ForumDataDto {
	private Long id;
	private Integer ver;
	private String titleForum;
	private String bodyForum;
	private String studentName;
	private String className;
	private LocalDateTime createdAt;

	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

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

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
