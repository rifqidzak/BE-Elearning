package com.lawencon.e.learning.dto.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.lawencon.e.learning.dto.file.FileInsertReqDto;

public class UserUpdateReqDataDto {
	@NotNull(message = "User Required")
	private Long id;

	@Size(max = 50, message = "Maximum Length 50")
	private String fullNameUser;
	private FileInsertReqDto fileInsertReqDto;

	private Long roleId;

	@NotNull(message = "Ver Required")
	private Integer ver;
	private Boolean isActive;

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

	public String getFullNameUser() {
		return fullNameUser;
	}

	public void setFullNameUser(String fullNameUser) {
		this.fullNameUser = fullNameUser;
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
