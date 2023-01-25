package com.lawencon.e.learning.dao;

import java.util.List;
import java.util.Optional;

import com.lawencon.e.learning.model.AttendanceExam;

public interface AttendanceExamDao {
	List<AttendanceExam> getAllbyStudentSide(Long studyClassDetailId);

	List<AttendanceExam> getAllbyTeacherSide(Long studyClassId);

	Optional<AttendanceExam> getById(Long id);

	Optional<AttendanceExam> getByStudentIdAndScheduleId(Long studentId, Long scheduleId);

	AttendanceExam insert(AttendanceExam data);

	AttendanceExam approving(AttendanceExam data);
}
