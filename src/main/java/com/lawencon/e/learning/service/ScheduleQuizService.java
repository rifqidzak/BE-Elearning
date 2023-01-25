package com.lawencon.e.learning.service;

import com.lawencon.e.learning.dto.schedulequiz.ScheduleQuizDto;
import com.lawencon.e.learning.dto.schedulequiz.ScheduleQuizsDto;

public interface ScheduleQuizService {

	ScheduleQuizsDto getAllByStudentSideForAttendance(Long studyClassId);

	ScheduleQuizsDto getAllByTeacherSide(Long classId);

	ScheduleQuizDto getById(Long id);
}
