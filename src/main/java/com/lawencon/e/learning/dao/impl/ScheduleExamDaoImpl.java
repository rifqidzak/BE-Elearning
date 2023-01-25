package com.lawencon.e.learning.dao.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.e.learning.dao.ScheduleExamDao;
import com.lawencon.e.learning.model.ScheduleExam;

@Repository
public class ScheduleExamDaoImpl extends BaseDaoImpl implements ScheduleExamDao {

	@Override
	public ScheduleExam insert(ScheduleExam data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public boolean delete(Long id) {
		final String sql = " DELETE FROM ScheduleExam WHERE id = :id ";

		final int result = this.em.createQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}

	@Override
	public Optional<ScheduleExam> getByFinishOnAndStartAt(LocalDateTime startAt, LocalDateTime finishOn,
			Long studyClassId) {
		final String sql = "SELECT se FROM ScheduleExam se  " + " INNER JOIN FETCH se.examId e "
				+ "	INNER JOIN FETCH e.studyClassId sc " + " INNER JOIN e.studyClassId sc "
				+ "	WHERE se.startAt = :startAt AND se.finishOn = :finishOn AND sc.id = :studyClassId";
		Optional<ScheduleExam> scheduleExamOptional = Optional.ofNullable(null);
		try {
			ScheduleExam scheduleExam = this.em.createQuery(sql, ScheduleExam.class).setParameter("startAt", startAt)
					.setParameter("finishOn", finishOn).setParameter("studyClassId", studyClassId).getSingleResult();
			this.em.detach(scheduleExam);
			scheduleExamOptional = Optional.ofNullable(scheduleExam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scheduleExamOptional;
	}

	@Override
	public List<ScheduleExam> getAllByStudentSideForAttendance(Long studyClassId) {
		final String sql = "SELECT se FROM ScheduleExam se " + " INNER JOIN FETCH se.examId e "
				+ " INNER JOIN FETCH e.studyClassId sc "
				+ " WHERE sc.id = :id AND current_date() BETWEEN se.startAt AND se.finishOn";
		List<ScheduleExam> scheduleExam = new ArrayList<>();
		scheduleExam = this.em.createQuery(sql, ScheduleExam.class).setParameter("id", studyClassId).getResultList();
		return scheduleExam;
	}

	
	@Override
	public List<ScheduleExam> getAllByTeacherSide(Long studyClassId) {
		final String sql = "SELECT se FROM ScheduleExam se" + " INNER JOIN FETCH se.examId e "
				+ " INNER JOIN FETCH e.studyClassId sc " + " WHERE sc.id = :id ";
		List<ScheduleExam> scheduleExams = new ArrayList<>();
		try {
			scheduleExams = this.em.createQuery(sql, ScheduleExam.class).setParameter("id", studyClassId)
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scheduleExams;
	}

	@Override
	public Optional<ScheduleExam> getById(Long id) {
		ScheduleExam scheduleExam = new ScheduleExam();
		Optional<ScheduleExam> scheduleExamOptional = Optional.ofNullable(null);
		try {
			scheduleExam = this.em.find(ScheduleExam.class, id);
			this.em.detach(scheduleExam);
			scheduleExamOptional = Optional.ofNullable(scheduleExam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scheduleExamOptional;
	}

}
