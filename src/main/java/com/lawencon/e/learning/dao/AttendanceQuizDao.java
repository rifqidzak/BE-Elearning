package com.lawencon.e.learning.dao;

import java.util.List;
import java.util.Optional;

import com.lawencon.e.learning.model.AttendanceQuiz;

public interface AttendanceQuizDao {
	List<AttendanceQuiz> getAllbyStudentSide(Long studyClassDetailId);

	List<AttendanceQuiz> getAllbyTeacherSide(Long studyClassId);

	Optional<AttendanceQuiz> getById(Long id);

	Optional<AttendanceQuiz> getByStudentIdAndScheduleId(Long studentId, Long scheduleId);

	AttendanceQuiz insert(AttendanceQuiz data);

	AttendanceQuiz approving(AttendanceQuiz data);
}
