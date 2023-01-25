package com.lawencon.e.learning.dto.theory;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.lawencon.e.learning.dto.file.FileInsertReqDto;

public class TheoryInsertReqDataDto {
	@NotBlank(message = "Code Required!")
	@Size(max = 12, message = "Code Range Max 12")
	private String theoryCode;
	
	@NotBlank(message = "Name Required!")
	@Size(max = 30, message = "Name Range Max 30")
	private String theoryName;
	
	@NotNull(message = "Class Not Found!")
	private Long studyClassId;
	
	private List<FileInsertReqDto> file;
	
	private LocalDate startAt;
	
	private LocalDate finishOn;

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

	public List<FileInsertReqDto> getFile() {
		return file;
	}

	public void setFile(List<FileInsertReqDto> file) {
		this.file = file;
	}

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

	public Long getStudyClassId() {
		return studyClassId;
	}

	public void setStudyClassId(Long studyClassId) {
		this.studyClassId = studyClassId;
	}

}
