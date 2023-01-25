package com.lawencon.e.learning.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.e.learning.dao.AttendanceTheoryDao;
import com.lawencon.e.learning.model.AttendanceTheory;

@Repository
public class AttendanceTheoryDaoImpl extends BaseDaoImpl implements AttendanceTheoryDao {

	@Override
	public List<AttendanceTheory> getAllbyStudentSide(Long studentId) {
		final String sql = "SELECT at FROM AttendanceTheory at " + " INNER JOIN FETCH at.scheduleId st "
				+ " INNER JOIN FETCH at.studyClassDetailId scd " + "	INNER JOIN FETCH st.theoryId t"
				+ " WHERE scd.id = :id AND at.approve = true";
		List<AttendanceTheory> attendanceTheories = new ArrayList<>();
		attendanceTheories = this.em.createQuery(sql, AttendanceTheory.class).setParameter("id", studentId)
				.getResultList();
		return attendanceTheories;
	}

	@Override
	public AttendanceTheory insert(AttendanceTheory data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public Optional<AttendanceTheory> getById(Long id) {
		AttendanceTheory attendanceTheory = new AttendanceTheory();
		Optional<AttendanceTheory> attendanceTheoryOptional = Optional.ofNullable(null);
		try {
			attendanceTheory = this.em.find(AttendanceTheory.class, id);
			this.em.detach(attendanceTheory);
			attendanceTheoryOptional = Optional.ofNullable(attendanceTheory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return attendanceTheoryOptional;
	}

	@Override
	public List<AttendanceTheory> getAllbyTeacherSide(Long studyClassId) {
		final String sql = "SELECT at FROM AttendanceTheory at " + " INNER JOIN FETCH at.scheduleId st "
				+ " INNER JOIN FETCH st.theoryId t " + "	INNER JOIN FETCH at.studentId "
				+ " INNER JOIN FETCH t.studyClassId sc" + " WHERE sc.id = :id";
		List<AttendanceTheory> attendanceTheories = new ArrayList<>();
		attendanceTheories = this.em.createQuery(sql, AttendanceTheory.class).setParameter("id", studyClassId)
				.getResultList();
		return attendanceTheories;
	}

	@Override
	public AttendanceTheory approving(AttendanceTheory data) {
		final AttendanceTheory attendanceTheoryUpdated = this.em.merge(data);
		this.em.flush();
		return attendanceTheoryUpdated;
	}

	@Override
	public Optional<AttendanceTheory> getByStudentIdAndScheduleId(Long studentId, Long scheduleId) {
		final String sql = "SELECT at FROM AttendanceTheory at " + " INNER JOIN FETCH at.studentId s "
				+ " INNER JOIN FETCH at.scheduleId st " + " WHERE s.id = :studentId AND st.id = :scheduleId ";
		Optional<AttendanceTheory> attendanceTheoryOptional = Optional.ofNullable(null);
		try {
			AttendanceTheory attendanceTheory = this.em.createQuery(sql, AttendanceTheory.class)
					.setParameter("studentId", studentId).setParameter("scheduleId", scheduleId).getSingleResult();
			this.em.detach(attendanceTheory);
			attendanceTheoryOptional = Optional.ofNullable(attendanceTheory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return attendanceTheoryOptional;
	}

}
