package com.lawencon.e.learning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_role_user")
public class RoleUser extends BaseModel  {
	
	@Column(name = "role_user_code", length = 8, unique = true)
	private String roleUserCode;
	
	@Column(name = "role_user_name", length = 15)
	private String roleUserName;
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
	
	
}
