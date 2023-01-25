package com.lawencon.e.learning.dto.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.lawencon.e.learning.dto.file.FileInsertReqDto;

public class UserInsertReqDataDto {
	@NotBlank(message = "Name Required !")
	@Size(max = 50, message = "Maximum Length 50")
	private String fullNameUser;
	
	@NotBlank(message = "Name Required !")
	@Size(max = 50, message = "Maximum Length 50")
	private String email;
	
	private FileInsertReqDto fileInsertReqDto;
	
	@NotNull(message = "Role Not Found! ")
	private Long roleId;
	
	public String getFullNameUser() {
		return fullNameUser;
	}
	public void setFullNameUser(String fullNameUser) {
		this.fullNameUser = fullNameUser;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public FileInsertReqDto getFileInsertReqDto() {
		return fileInsertReqDto;
	}
	public void setFileInsertReqDto(FileInsertReqDto fileInsertReqDto) {
		this.fileInsertReqDto = fileInsertReqDto;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	
}
