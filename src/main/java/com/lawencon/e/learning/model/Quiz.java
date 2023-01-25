package com.lawencon.e.learning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_quiz")
public class Quiz extends BaseModel {
	@Column(name = "quiz_code", length = 12, unique = true)
	private String quizCode;
	
	@Column(name = "quiz_name", length = 30)
	private String quizName;
	
	@ManyToOne 
	@JoinColumn(name = "study_class_id", nullable = false)
	private StudyClass studyClassId;
	
	public String getQuizCode() {
		return quizCode;
	}
	public void setQuizCode(String quizCode) {
		this.quizCode = quizCode;
	}
	public String getQuizName() {
		return quizName;
	}
	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}
	public StudyClass getStudyClassId() {
		return studyClassId;
	}
	public void setStudyClassId(StudyClass studyClassId) {
		this.studyClassId = studyClassId;
	}
	
	
	
		
}
