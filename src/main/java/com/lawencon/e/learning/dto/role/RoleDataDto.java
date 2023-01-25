package com.lawencon.e.learning.dto.role;

public class RoleDataDto {
	private Long id;
	private String roleUserCode;
	private String roleUserName;
	private Integer ver;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleUserCode() {
		return roleUserCode;
	}
	public void setRoleUserCode(String roleUserCode) {
		this.roleUserCode = roleUserCode;
	}
	public String getRoleUserName() {
		return roleUserName;
	}
	public void setRoleUserName(String roleUserName) {
		this.roleUserName = roleUserName;
	}
	public Integer getVer() {
		return ver;
	}
	public void setVer(Integer ver) {
		this.ver = ver;
	}
	
	
}
