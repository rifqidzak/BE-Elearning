package com.lawencon.e.learning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_study_class")
public class StudyClass extends BaseModel {
	@Column(name = "study_class_code" , length = 12, nullable = false)
	private String studyClassCode;
	
	@Column(name = "study_class_name" , length = 30)
	private String studyClassName;
	
	@Column(name = "study_class_description" , length = 100)
	private String studyClassDescription;
	
	@ManyToOne 
	@JoinColumn(name = "teacher_id", nullable = false)
	private User teacherId;
	
	@ManyToOne 
	@JoinColumn(name = "photo_study_class")
	private File photoClass;
	
	public String getStudyClassCode() {
		return studyClassCode;
	}
	public void setStudyClassCode(String studyClassCode) {
		this.studyClassCode = studyClassCode;
	}
	public String getStudyClassName() {
		return studyClassName;
	}
	public void setStudyClassName(String studyClassName) {
		this.studyClassName = studyClassName;
	}
	public String getStudyClassDescription() {
		return studyClassDescription;
	}
	public void setStudyClassDescription(String studyClassDescription) {
		this.studyClassDescription = studyClassDescription;
	}
	public User getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(User teacherId) {
		this.teacherId = teacherId;
	}
	public File getPhotoClass() {
		return photoClass;
	}
	public void setPhotoClass(File photoClass) {
		this.photoClass = photoClass;
	}
	

}
