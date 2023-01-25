package com.lawencon.e.learning.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.lawencon.e.learning.model.ScheduleTheory;

public interface ScheduleTheoryDao {
	ScheduleTheory insert(ScheduleTheory data);

	boolean delete(Long id);

	Optional<ScheduleTheory> getByFinishOnAndStartAt(LocalDateTime startAt, LocalDateTime finishOn, Long theoryId);

	List<ScheduleTheory> getAllByStudentSideForAttendance(Long studyClassId);

	List<ScheduleTheory> getAllByTeacherSide(Long classId);

	Optional<ScheduleTheory> getById(Long id);
}
