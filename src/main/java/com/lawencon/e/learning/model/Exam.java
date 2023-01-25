package com.lawencon.e.learning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_exam")
public class Exam extends BaseModel {
	
	@Column(name = "exam_code", length = 12, unique = true)
	private String examCode;
	
	@Column(name = "exam_name", length = 30)
	private String examName;
	
	@ManyToOne 
	@JoinColumn(name = "study_class_id", nullable = false)
	private StudyClass studyClassId;

	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public StudyClass getStudyClassId() {
		return studyClassId;
	}

	public void setStudyClassId(StudyClass studyClassId) {
		this.studyClassId = studyClassId;
	}

}
