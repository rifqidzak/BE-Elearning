package com.lawencon.e.learning.dto.comment;

public class CommentInsertResDto {
	private CommentInsertResDataDto data;
	private String message;

	public CommentInsertResDataDto getData() {
		return data;
	}

	public void setData(CommentInsertResDataDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
