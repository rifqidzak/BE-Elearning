package com.lawencon.e.learning.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.e.learning.dao.CommentDao;
import com.lawencon.e.learning.model.Comment;

@Repository
public class CommentDaoImpl extends BaseDaoImpl implements CommentDao {

	@Override
	public List<Comment> getAll(Long forumId) {
		final String sql = "SELECT c FROM Comment c" + " INNER JOIN FETCH c.forumId f " + " WHERE f.id = :id ";
		List<Comment> comments = new ArrayList<>();
		comments = this.em.createQuery(sql, Comment.class).setParameter("id", forumId).getResultList();
		return comments;
	}

	@Override
	public Comment insert(Comment data) {
		this.em.persist(data);

		return data;
	}

}
