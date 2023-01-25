package com.lawencon.e.learning.service;

import com.lawencon.e.learning.dto.scheduleexam.ScheduleExamDto;
import com.lawencon.e.learning.dto.scheduleexam.ScheduleExamsDto;

public interface ScheduleExamService {

	ScheduleExamsDto getAllByStudentSideForAttendance(Long studyClassId);

	ScheduleExamsDto getAllByTeacherSide(Long classId);

	ScheduleExamDto getById(Long id);
}
