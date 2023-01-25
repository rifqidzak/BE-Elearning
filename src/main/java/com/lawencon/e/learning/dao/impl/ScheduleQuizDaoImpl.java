package com.lawencon.e.learning.dao.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.e.learning.dao.ScheduleQuizDao;
import com.lawencon.e.learning.model.ScheduleQuiz;

@Repository
public class ScheduleQuizDaoImpl extends BaseDaoImpl implements ScheduleQuizDao {

	@Override
	public ScheduleQuiz insert(ScheduleQuiz data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public boolean delete(Long id) {
		final String sql = " DELETE FROM ScheduleQuiz WHERE id = :id ";

		final int result = this.em.createQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}

	@Override
	public Optional<ScheduleQuiz> getByFinishOnAndStartAt(LocalDateTime startAt, LocalDateTime finishOn,
			Long studyClassId) {
		final String sql = "SELECT sq FROM ScheduleQuiz sq  " + " INNER JOIN FETCH sq.quizId q "
				+ " INNER JOIN q.studyClassId sc "
				+ "	WHERE sq.startAt = :startAt AND sq.finishOn = :finishOn AND sc.id = :studyClassId";
		Optional<ScheduleQuiz> scheduleQuizOptional = Optional.ofNullable(null);
		try {
			ScheduleQuiz scheduleQuiz = this.em.createQuery(sql, ScheduleQuiz.class).setParameter("startAt", startAt)
					.setParameter("finishOn", finishOn).setParameter("studyClassId", studyClassId).getSingleResult();
			this.em.detach(scheduleQuiz);
			scheduleQuizOptional = Optional.ofNullable(scheduleQuiz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scheduleQuizOptional;
	}

	@Override
	public List<ScheduleQuiz> getAllByStudentSideForAttendance(Long studyClassId) {
		final String sql = "SELECT sq FROM ScheduleQuiz sq " + " INNER JOIN FETCH sq.quizId q "
				+ " INNER JOIN FETCH q.studyClassId sc "
				+ " WHERE sc.id = :id AND current_date() BETWEEN sq.startAt AND sq.finishOn";
		List<ScheduleQuiz> scheduleQuiz = new ArrayList<>();
		scheduleQuiz = this.em.createQuery(sql, ScheduleQuiz.class).setParameter("id", studyClassId).getResultList();
		return scheduleQuiz;
	}


	@Override
	public List<ScheduleQuiz> getAllByTeacherSide(Long studyClassId) {
		final String sql = "SELECT sq FROM ScheduleQuiz sq" + " INNER JOIN FETCH sq.quizId q "
				+ " INNER JOIN FETCH q.studyClassId sc " + " WHERE sc.id = :id ";
		List<ScheduleQuiz> scheduleQuizs = new ArrayList<>();
		scheduleQuizs = this.em.createQuery(sql, ScheduleQuiz.class).setParameter("id", studyClassId).getResultList();
		return scheduleQuizs;
	}

	@Override
	public Optional<ScheduleQuiz> getById(Long id) {
		ScheduleQuiz scheduleQuiz = new ScheduleQuiz();
		Optional<ScheduleQuiz> scheduleQuizOptional = Optional.ofNullable(null);
		try {
			scheduleQuiz = this.em.find(ScheduleQuiz.class, id);
			this.em.detach(scheduleQuiz);
			scheduleQuizOptional = Optional.ofNullable(scheduleQuiz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scheduleQuizOptional;
	}

}
