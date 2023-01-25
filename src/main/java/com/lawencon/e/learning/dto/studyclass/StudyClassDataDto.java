package com.lawencon.e.learning.dto.studyclass;

public class StudyClassDataDto {
	private Long id;
	private Integer ver;
	private String studyClassCode;
	private String studyClassName;
	private String teacherName;
	private Long photoStudyClass;
	private Boolean isActive;
	private String description;
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public Long getPhotoStudyClass() {
		return photoStudyClass;
	}
	public void setPhotoStudyClass(Long photoStudyClass) {
		this.photoStudyClass = photoStudyClass;
	}
}
