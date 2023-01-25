package com.lawencon.e.learning.dao.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.e.learning.dao.QuizDao;
import com.lawencon.e.learning.model.Quiz;

@Repository
public class QuizDaoImpl extends BaseDaoImpl implements QuizDao {

	@Override
	public Quiz insert(Quiz data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public boolean deleteById(Long id) {
		final String sql = " DELETE FROM Quiz WHERE id = :id ";

		final int result = this.em.createQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}

	@Override
	public Optional<Quiz> getById(Long id) {
		Quiz quiz = new Quiz();
		Optional<Quiz> quizOptional = Optional.ofNullable(null);
		try {
			quiz = this.em.find(Quiz.class, id);
			this.em.detach(quiz);
			quizOptional = Optional.ofNullable(quiz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quizOptional;
	}

}
