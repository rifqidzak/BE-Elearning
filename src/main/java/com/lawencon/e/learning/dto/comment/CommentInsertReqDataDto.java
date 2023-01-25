package com.lawencon.e.learning.dto.comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CommentInsertReqDataDto {
	@NotBlank(message = "Comment Required")
	private String bodyComment;
	
	@NotNull(message = "Forum Not Found!")
	private Long forumId;

	public String getBodyComment() {
		return bodyComment;
	}

	public void setBodyComment(String bodyComment) {
		this.bodyComment = bodyComment;
	}

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}
	
}
