package com.lawencon.e.learning.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.e.learning.dao.AttachmentQuizDao;
import com.lawencon.e.learning.model.AttachmentQuiz;

@Repository
public class AttachmentQuizDaoImpl extends BaseDaoImpl implements AttachmentQuizDao {

	@Override
	public AttachmentQuiz insert(AttachmentQuiz data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public List<AttachmentQuiz> getByQuizId(Long id) {
		final String sql = "SELECT aq FROM AttachmentQuiz aq " + " INNER JOIN aq.quizId q " + " WHERE q.id = :id ";
		List<AttachmentQuiz> attachmentQuiz = new ArrayList<>();
		attachmentQuiz = this.em.createQuery(sql, AttachmentQuiz.class).setParameter("id", id).getResultList();
		return attachmentQuiz;
	}

}
