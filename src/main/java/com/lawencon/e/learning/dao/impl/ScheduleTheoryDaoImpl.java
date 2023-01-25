package com.lawencon.e.learning.dao.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.e.learning.dao.ScheduleTheoryDao;
import com.lawencon.e.learning.model.ScheduleTheory;

@Repository
public class ScheduleTheoryDaoImpl extends BaseDaoImpl implements ScheduleTheoryDao {

	@Override
	public ScheduleTheory insert(ScheduleTheory data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public boolean delete(Long id) {
		final String sql = " DELETE FROM ScheduleTheory WHERE id = :id ";

		final int result = this.em.createQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}

	@Override
	public Optional<ScheduleTheory> getByFinishOnAndStartAt(LocalDateTime startAt, LocalDateTime finishOn,
			Long studyClassId) {
		final String sql = "SELECT st FROM ScheduleTheory st  " + " INNER JOIN FETCH st.theoryId t "
				+ " INNER JOIN FETCH t.studyClassId sc "
				+ "	WHERE st.startAt = :startAt AND st.finishOn = :finishOn AND sc.id = :studyClassId";
		Optional<ScheduleTheory> scheduleTheoryOptional = Optional.ofNullable(null);
		try {
			ScheduleTheory scheduleTheory = this.em.createQuery(sql, ScheduleTheory.class)
					.setParameter("startAt", startAt).setParameter("finishOn", finishOn)
					.setParameter("studyClassId", studyClassId).getSingleResult();
			this.em.detach(scheduleTheory);
			scheduleTheoryOptional = Optional.ofNullable(scheduleTheory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scheduleTheoryOptional;
	}

	@Override
	public List<ScheduleTheory> getAllByStudentSideForAttendance(Long studyClassId) {
		final String sql = "SELECT st FROM ScheduleTheory st " + " INNER JOIN FETCH st.theoryId t "
				+ " INNER JOIN FETCH t.studyClassId sc "
				+ " WHERE sc.id = :id AND current_date() BETWEEN st.startAt AND st.finishOn";
		List<ScheduleTheory> scheduleTheories = new ArrayList<>();
		scheduleTheories = this.em.createQuery(sql, ScheduleTheory.class).setParameter("id", studyClassId)
				.getResultList();
		return scheduleTheories;
	}

	@Override
	public Optional<ScheduleTheory> getById(Long id) {
		ScheduleTheory scheduleTheory = new ScheduleTheory();
		Optional<ScheduleTheory> scheduleTheoryOptional = Optional.ofNullable(null);
		try {
			scheduleTheory = this.em.find(ScheduleTheory.class, id);
			this.em.detach(scheduleTheory);
			scheduleTheoryOptional = Optional.ofNullable(scheduleTheory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scheduleTheoryOptional;
	}

	@Override
	public List<ScheduleTheory> getAllByTeacherSide(Long studyClassId) {
		final String sql = "SELECT st FROM ScheduleTheory st" + " INNER JOIN FETCH st.theoryId t "
				+ " INNER JOIN FETCH t.studyClassId sc " + " WHERE sc.id = :id ";
		List<ScheduleTheory> scheduleTheories = new ArrayList<>();
		scheduleTheories = this.em.createQuery(sql, ScheduleTheory.class).setParameter("id", studyClassId)
				.getResultList();
		return scheduleTheories;
	}

}
