package com.lawencon.e.learning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_comment")
public class Comment extends BaseModel{
	@Column(name = "body_comment")
	private String bodyComment;
	
	@ManyToOne 
	@JoinColumn(name = "forum_id" ,nullable = false)
	private Forum forumId;
	
	public String getBodyComment() {
		return bodyComment;
	}
	public void setBodyComment(String bodyComment) {
		this.bodyComment = bodyComment;
	}
	public Forum getForumId() {
		return forumId;
	}
	public void setForumId(Forum forumId) {
		this.forumId = forumId;
	}
}
