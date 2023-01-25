package com.lawencon.e.learning.dao;

import java.util.List;

import com.lawencon.e.learning.model.Comment;

public interface CommentDao {
	List<Comment> getAll(Long forumId);

	Comment insert(Comment data);
}
