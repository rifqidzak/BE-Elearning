package com.lawencon.e.learning.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.lawencon.e.learning.model.ScheduleQuiz;

public interface ScheduleQuizDao {
	ScheduleQuiz insert(ScheduleQuiz data);

	boolean delete(Long id);

	Optional<ScheduleQuiz> getByFinishOnAndStartAt(LocalDateTime startAt, LocalDateTime finishOn, Long studyClassId);

	List<ScheduleQuiz> getAllByStudentSideForAttendance(Long studyClassId);

	List<ScheduleQuiz> getAllByTeacherSide(Long classId);

	Optional<ScheduleQuiz> getById(Long id);
}
