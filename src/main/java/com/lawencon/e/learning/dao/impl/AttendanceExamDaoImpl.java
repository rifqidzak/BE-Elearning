package com.lawencon.e.learning.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.e.learning.dao.AttendanceExamDao;
import com.lawencon.e.learning.model.AttendanceExam;

@Repository
public class AttendanceExamDaoImpl extends BaseDaoImpl implements AttendanceExamDao {

	@Override
	public List<AttendanceExam> getAllbyStudentSide(Long studyClassDetailId) {
		final String sql = "SELECT ae FROM AttendanceExam ae " + " INNER JOIN FETCH ae.scheduleId se "
				+ " INNER JOIN FETCH ae.studyClassDetailId scd " + " INNER JOIN FETCH se.examId e"
				+ " WHERE scd.id = :id AND ae.approve = true";
		List<AttendanceExam> attendanceExams = new ArrayList<>();
		attendanceExams = this.em.createQuery(sql, AttendanceExam.class).setParameter("id", studyClassDetailId)
				.getResultList();
		return attendanceExams;
	}

	@Override
	public List<AttendanceExam> getAllbyTeacherSide(Long studyClassId) {
		final String sql = "SELECT ae FROM AttendanceExam ae " + " INNER JOIN FETCH ae.scheduleId se "
				+ " INNER JOIN FETCH se.examId e " + "	INNER JOIN FETCH ae.studentId "
				+ " INNER JOIN FETCH e.studyClassId sc" + " WHERE sc.id = :id";
		List<AttendanceExam> attendanceExams = new ArrayList<>();
		attendanceExams = this.em.createQuery(sql, AttendanceExam.class).setParameter("id", studyClassId)
				.getResultList();
		return attendanceExams;
	}

	@Override
	public Optional<AttendanceExam> getById(Long id) {
		Optional<AttendanceExam> attendanceExamOptional = Optional.ofNullable(null);
		try {
			AttendanceExam attendanceExam = new AttendanceExam();
			attendanceExam = this.em.find(AttendanceExam.class, id);
			this.em.detach(attendanceExam);
			attendanceExamOptional = Optional.ofNullable(attendanceExam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return attendanceExamOptional;
	}

	@Override
	public Optional<AttendanceExam> getByStudentIdAndScheduleId(Long studentId, Long scheduleId) {
		final String sql = "SELECT ae FROM AttendanceExam ae " + " INNER JOIN FETCH ae.studentId s "
				+ " INNER JOIN FETCH ae.scheduleId se " + " WHERE s.id = :studentId AND se.id = :scheduleId ";
		Optional<AttendanceExam> attendanceExamOptional = Optional.ofNullable(null);
		try {
			AttendanceExam attendanceExam = this.em.createQuery(sql, AttendanceExam.class)
					.setParameter("studentId", studentId).setParameter("scheduleId", scheduleId).getSingleResult();
			this.em.detach(attendanceExam);
			attendanceExamOptional = Optional.ofNullable(attendanceExam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return attendanceExamOptional;
	}

	@Override
	public AttendanceExam insert(AttendanceExam data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public AttendanceExam approving(AttendanceExam data) {
		final AttendanceExam attendanceExamUpdated = this.em.merge(data);
		this.em.flush();
		return attendanceExamUpdated;
	}

}
