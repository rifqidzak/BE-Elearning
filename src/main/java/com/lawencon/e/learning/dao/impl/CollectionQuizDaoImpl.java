package com.lawencon.e.learning.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.e.learning.dao.CollectionQuizDao;
import com.lawencon.e.learning.model.CollectionQuiz;

@Repository
public class CollectionQuizDaoImpl extends BaseDaoImpl implements CollectionQuizDao {

	@Override
	public CollectionQuiz insert(CollectionQuiz data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public Optional<CollectionQuiz> getById(Long id) {
		CollectionQuiz collectionQuiz = new CollectionQuiz();
		Optional<CollectionQuiz> collectionQuizOptional = Optional.ofNullable(null);
		try {
			collectionQuiz = this.em.find(CollectionQuiz.class, id);
			this.em.detach(collectionQuiz);
			collectionQuizOptional = Optional.ofNullable(collectionQuiz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return collectionQuizOptional;
	}

	@Override
	public List<CollectionQuiz> getAllByTeacherSide(Long studyClassId) {
		final String sql = "SELECT cq FROM CollectionQuiz cq " + "	INNER JOIN FETCH cq.userId s"
				+ " INNER JOIN FETCH cq.scheduleId sq" + "	INNER JOIN FETCH sq.quizId q "
				+ " INNER JOIN FETCH q.studyClassId sc" + " WHERE sc.id = :studyClassId ";
		List<CollectionQuiz> collectionQuiz = new ArrayList<>();
		collectionQuiz = this.em.createQuery(sql, CollectionQuiz.class).setParameter("studyClassId", studyClassId)
				.getResultList();
		return collectionQuiz;
	}

	@Override
	public CollectionQuiz scoring(CollectionQuiz data) {
		final CollectionQuiz collectionQuizUpdated = this.em.merge(data);

		return collectionQuizUpdated;
	}

	@Override
	public Optional<CollectionQuiz> getByScheduleIdAndStudentId(Long scheduleId, Long studentId) {
		final String sql = "SELECT cq FROM CollectionQuiz cq " + " INNER JOIN FETCH cq.scheduleId sq "
				+ "	INNER JOIN FETCH cq.userId u " + " WHERE sq.id = :scheduleId AND u.id = :userId";
		Optional<CollectionQuiz> collectionQuizOptional = Optional.ofNullable(null);
		try {
			CollectionQuiz collectionQuiz = new CollectionQuiz();
			this.em.detach(collectionQuiz);
			collectionQuizOptional = Optional.ofNullable(collectionQuiz);
			collectionQuiz = this.em.createQuery(sql, CollectionQuiz.class).setParameter("scheduleId", scheduleId)
					.setParameter("userId", studentId).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return collectionQuizOptional;
	}

}
