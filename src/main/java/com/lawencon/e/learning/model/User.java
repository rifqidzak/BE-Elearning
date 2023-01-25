package com.lawencon.e.learning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_user")
public class User extends BaseModel {
	@Column(name = "full_name_user", length = 50)
	private String fullNameUser;
	
	@Column(name = "email", length = 50, unique = true)
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@ManyToOne 
	@JoinColumn(name = "photo_user")
	private File photoUser;
	
	@ManyToOne 
	@JoinColumn(name = "role_user_id")
	private RoleUser roleUserId;

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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public File getPhotoUser() {
		return photoUser;
	}
	public void setPhotoUser(File photoUser) {
		this.photoUser = photoUser;
	}
	public RoleUser getRoleUserId() {
		return roleUserId;
	}
	public void setRoleUserId(RoleUser roleUserId) {
		this.roleUserId = roleUserId;
	}
	

	
	
}
