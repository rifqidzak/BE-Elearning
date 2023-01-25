package com.lawencon.e.learning.dto.comment;

public class CommentDataDto {
	private Integer ver;
	private Long id;
	private String bodyComment;
	private String name;

	
	public Integer getVer() {
		return ver;
	}

	public void setVer(Integer ver) {
		this.ver = ver;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBodyComment() {
		return bodyComment;
	}

	public void setBodyComment(String bodyComment) {
		this.bodyComment = bodyComment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
