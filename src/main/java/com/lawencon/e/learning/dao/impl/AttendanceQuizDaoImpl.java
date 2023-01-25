package com.lawencon.e.learning.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.e.learning.dao.AttendanceQuizDao;
import com.lawencon.e.learning.model.AttendanceQuiz;

@Repository
public class AttendanceQuizDaoImpl extends BaseDaoImpl implements AttendanceQuizDao {

	@Override
	public List<AttendanceQuiz> getAllbyStudentSide(Long studentId) {
		final String sql = "SELECT aq FROM AttendanceQuiz aq " + " INNER JOIN FETCH aq.scheduleId sq "
				+ " INNER JOIN FETCH aq.studyClassDetailId scd " + "	INNER JOIN FETCH sq.quizId q"
				+ " WHERE scd.id = :id AND aq.approve = true";
		List<AttendanceQuiz> attendanceQuizs = new ArrayList<>();
		attendanceQuizs = this.em.createQuery(sql, AttendanceQuiz.class).setParameter("id", studentId).getResultList();
		return attendanceQuizs;
	}

	@Override
	public List<AttendanceQuiz> getAllbyTeacherSide(Long studyClassId) {
		final String sql = "SELECT aq FROM AttendanceQuiz aq " + " INNER JOIN FETCH aq.scheduleId sq "
				+ " INNER JOIN FETCH sq.quizId q " + "	INNER JOIN FETCH aq.studentId "
				+ " INNER JOIN FETCH q.studyClassId sc" + " WHERE sc.id = :id";
		List<AttendanceQuiz> attendanceQuizs = new ArrayList<>();
		attendanceQuizs = this.em.createQuery(sql, AttendanceQuiz.class).setParameter("id", studyClassId)
				.getResultList();
		return attendanceQuizs;
	}

	@Override
	public Optional<AttendanceQuiz> getById(Long id) {
		AttendanceQuiz attendanceQuiz = new AttendanceQuiz();
		Optional<AttendanceQuiz> attendanceQuizOptional = Optional.ofNullable(null);
		try {
			attendanceQuiz = this.em.find(AttendanceQuiz.class, id);
			this.em.detach(attendanceQuiz);
			attendanceQuizOptional = Optional.ofNullable(attendanceQuiz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return attendanceQuizOptional;
	}

	@Override
	public Optional<AttendanceQuiz> getByStudentIdAndScheduleId(Long studentId, Long scheduleId) {
		final String sql = "SELECT aq FROM AttendanceQuiz aq " + " INNER JOIN FETCH aq.studentId s "
				+ " INNER JOIN FETCH aq.scheduleId sq " + " WHERE s.id = :studentId AND sq.id = :scheduleId ";
		Optional<AttendanceQuiz> attendanceQuizOptional = Optional.ofNullable(null);
		try {
			AttendanceQuiz attendanceQuiz = this.em.createQuery(sql, AttendanceQuiz.class)
					.setParameter("studentId", studentId).setParameter("scheduleId", scheduleId).getSingleResult();
			this.em.detach(attendanceQuiz);
			attendanceQuizOptional = Optional.ofNullable(attendanceQuiz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return attendanceQuizOptional;
	}

	@Override
	public AttendanceQuiz insert(AttendanceQuiz data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public AttendanceQuiz approving(AttendanceQuiz data) {
		final AttendanceQuiz attendanceQuizUpdated = this.em.merge(data);
		this.em.flush();
		return attendanceQuizUpdated;
	}

}
