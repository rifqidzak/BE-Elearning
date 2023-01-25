package com.lawencon.e.learning.dto.file;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class FileInsertReqDto {
	@NotBlank(message = "File Required")
	private String file;
	
	@NotBlank(message = "Extensions Required")
	@Size(max = 5, message = "Range Extensions Maximum 5")
	private String extension;
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	
}
