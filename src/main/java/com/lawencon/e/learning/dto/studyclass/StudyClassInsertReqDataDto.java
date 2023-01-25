package com.lawencon.e.learning.dto.studyclass;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.lawencon.e.learning.dto.file.FileInsertReqDto;

public class StudyClassInsertReqDataDto {
	@NotBlank(message = "Code Required")
	@Size(max = 12, message = "Max Length Code 12")
	private String studyClassCode;
	
	@NotBlank(message = "Class Name Required")
	@Size(max = 30, message = "Max Length Name 30")
	private String studyClassName;
	
	@Size(max = 100, message = "Max Length Description 100")
	private String studyClassDescription;
	
	@NotNull(message = "Teacher Not Found!")
	private Long teacherId;
	
	private FileInsertReqDto photoStudyClass;
	
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
	public Long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	public FileInsertReqDto getPhotoStudyClass() {
		return photoStudyClass;
	}
	public void setPhotoStudyClass(FileInsertReqDto photoStudyClass) {
		this.photoStudyClass = photoStudyClass;
	}
}
