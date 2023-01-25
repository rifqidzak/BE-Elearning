package com.lawencon.e.learning.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.lawencon.e.learning.model.ScheduleExam;

public interface ScheduleExamDao {
	ScheduleExam insert(ScheduleExam data);

	boolean delete(Long id);

	Optional<ScheduleExam> getByFinishOnAndStartAt(LocalDateTime startAt, LocalDateTime finishOn, Long examId);

	List<ScheduleExam> getAllByStudentSideForAttendance(Long studyClassId);

	List<ScheduleExam> getAllByTeacherSide(Long classId);

	Optional<ScheduleExam> getById(Long id);
}
