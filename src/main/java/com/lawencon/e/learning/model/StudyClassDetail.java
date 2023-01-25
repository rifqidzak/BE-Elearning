package com.lawencon.e.learning.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_study_class_detail")
public class StudyClassDetail extends BaseModel {
	@ManyToOne 
	@JoinColumn(name = "study_class_id", nullable = false)
	private StudyClass studyClassId;
	
	@ManyToOne 
	@JoinColumn(name = "student_id", nullable = false)
	private User studentId;
	

	public StudyClass getStudyClassId() {
		return studyClassId;
	}
	public void setStudyClassId(StudyClass studyClassId) {
		this.studyClassId = studyClassId;
	}
	public User getStudentId() {
		return studentId;
	}
	public void setStudentId(User studentId) {
		this.studentId = studentId;
	}
}
