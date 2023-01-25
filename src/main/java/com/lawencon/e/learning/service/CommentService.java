package com.lawencon.e.learning.service;

import com.lawencon.e.learning.dto.comment.CommentInsertReqDataDto;
import com.lawencon.e.learning.dto.comment.CommentInsertResDto;
import com.lawencon.e.learning.dto.comment.CommentsDto;

public interface CommentService {
	CommentsDto getAll(Long forumId);

	CommentInsertResDto insert(CommentInsertReqDataDto data);
}
