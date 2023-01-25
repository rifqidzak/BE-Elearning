package com.lawencon.e.learning.dto.exam;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.lawencon.e.learning.dto.file.FileInsertReqDto;

public class ExamInsertReqDataDto {
	@NotBlank(message = "Code Required!")
	@Size(max = 12, message = "Code Range Max 12")
	private String examCode;
	
	@NotBlank(message = "Name Required!")
	@Size(max = 30, message = "Name Range Max 30")
	private String examName;
	
	@NotNull(message = "Class Not Found!")
	private Long studyClassId;
	
	private List<FileInsertReqDto> file;
	
	private LocalDate startAt;
	
	private LocalDate finishOn;

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

	public Long getStudyClassId() {
		return studyClassId;
	}

	public void setStudyClassId(Long studyClassId) {
		this.studyClassId = studyClassId;
	}

	public List<FileInsertReqDto> getFile() {
		return file;
	}

	public void setFile(List<FileInsertReqDto> file) {
		this.file = file;
	}

	public LocalDate getStartAt() {
		return startAt;
	}

	public void setStartAt(LocalDate startAt) {
		this.startAt = startAt;
	}

	public LocalDate getFinishOn() {
		return finishOn;
	}

	public void setFinishOn(LocalDate finishOn) {
		this.finishOn = finishOn;
	}

}
