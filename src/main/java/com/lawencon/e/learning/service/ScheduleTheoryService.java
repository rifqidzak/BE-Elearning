package com.lawencon.e.learning.service;

import com.lawencon.e.learning.dto.scheduletheory.ScheduleTheoriesDto;
import com.lawencon.e.learning.dto.scheduletheory.ScheduleTheoryDto;

public interface ScheduleTheoryService {

	ScheduleTheoriesDto getAllByStudentSideForAttendance(Long studyClassId);

	ScheduleTheoriesDto getAllByTeacherSide(Long classId);

	ScheduleTheoryDto getById(Long id);
}
