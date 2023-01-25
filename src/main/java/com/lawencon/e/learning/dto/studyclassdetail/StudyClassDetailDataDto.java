package com.lawencon.e.learning.dto.studyclassdetail;

public class StudyClassDetailDataDto {
	private String teacherName;
	private String className;
	private String classCode;
	private Long studyClassId;
	private Long photoClassId;
	private Long id;
	private Integer ver;
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getStudyClassId() {
		return studyClassId;
	}
	public void setStudyClassId(Long studyClassId) {
		this.studyClassId = studyClassId;
	}
	public Long getPhotoClassId() {
		return photoClassId;
	}
	public void setPhotoClassId(Long photoClassId) {
		this.photoClassId = photoClassId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
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
	
	
}
