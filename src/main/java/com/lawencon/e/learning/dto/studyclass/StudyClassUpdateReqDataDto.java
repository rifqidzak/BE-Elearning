package com.lawencon.e.learning.dto.studyclass;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.lawencon.e.learning.dto.file.FileInsertReqDto;

public class StudyClassUpdateReqDataDto {
	@NotNull(message = "ID Required")
	private Long id;
	
	@NotNull(message = "Version Required")
	private Integer ver;
	
	private Long teacherId;
	
	@Size(max = 30, message = "Max Length Name 30")
	private String studyClassName;
	
	@Size(max = 100, message = "Max Length Description 100")
	private String studyClassDescription;
	
	private FileInsertReqDto photoStudyClass;
	
	private Boolean isActive;
	
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
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
	public FileInsertReqDto getPhotoStudyClass() {
		return photoStudyClass;
	}
	public void setPhotoStudyClass(FileInsertReqDto photoStudyClass) {
		this.photoStudyClass = photoStudyClass;
	}
}
