package com.lawencon.e.learning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_forum")
public class Forum extends BaseModel {
	@Column(name = "title_forum", length = 40, unique = true)
	private String titleForum;
	
	@Column(name = "body_forum")
	private String bodyForum;
	
	@ManyToOne 
	@JoinColumn(name = "student_id", nullable = false)
	private User studentId;
	
	@ManyToOne 
	@JoinColumn(name = "study_class_detail_id", nullable = false)
	private StudyClassDetail studyClassDetailId;
	
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
	public User getStudentId() {
		return studentId;
	}
	public void setStudentId(User studentId) {
		this.studentId = studentId;
	}
	public StudyClassDetail getStudyClassDetailId() {
		return studyClassDetailId;
	}
	public void setStudyClassDetailId(StudyClassDetail studyClassDetailId) {
		this.studyClassDetailId = studyClassDetailId;
	}
	
}
