package com.lawencon.e.learning.dto.collectionquiz;

import javax.validation.constraints.NotNull;

import com.lawencon.e.learning.dto.file.FileInsertReqDto;

public class CollectionQuizInsertReqDataDto {
	
	private Long scheduleId;
	
	@NotNull(message = "File Required")
	private FileInsertReqDto file;

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public FileInsertReqDto getFile() {
		return file;
	}

	public void setFile(FileInsertReqDto file) {
		this.file = file;
	}

}
