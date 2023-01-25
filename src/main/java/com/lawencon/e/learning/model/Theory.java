package com.lawencon.e.learning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_theory")
public class Theory extends BaseModel {
	@Column(name = "theory_code", length = 12, unique = true)
	private String theoryCode;
	
	@Column(name = "theory_name", length = 30)
	private String theoryName;
	
	@ManyToOne 
	@JoinColumn(name = "study_class_id", nullable = false)
	private StudyClass studyClassId;
	public String getTheoryCode() {
		return theoryCode;
	}
	public void setTheoryCode(String theoryCode) {
		this.theoryCode = theoryCode;
	}
	public String getTheoryName() {
		return theoryName;
	}
	public void setTheoryName(String theoryName) {
		this.theoryName = theoryName;
	}
	public StudyClass getStudyClassId() {
		return studyClassId;
	}
	public void setStudyClassId(StudyClass studyClassId) {
		this.studyClassId = studyClassId;
	}
	
}
