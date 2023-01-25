package com.lawencon.e.learning.service;

import com.lawencon.e.learning.dto.attendancetheory.AttendanceTheoryDto;
import com.lawencon.e.learning.dto.attendancetheory.AttendanceTheoryInsertReqDataDto;
import com.lawencon.e.learning.dto.attendancetheory.AttendanceTheoryInsertResDto;
import com.lawencon.e.learning.dto.attendancetheory.AttendanceTheoryUpdateReqDataDto;
import com.lawencon.e.learning.dto.attendancetheory.AttendanceTheoryUpdateResDto;
import com.lawencon.e.learning.dto.attendancetheory.AttendanceTheorysDto;

public interface AttendanceTheoryService {
	AttendanceTheorysDto getAllbyStudentSide(Long studyClassDetailId);

	AttendanceTheorysDto getAllbyTeacherSide(Long studyClassId);

	AttendanceTheoryDto getById(Long id);

	AttendanceTheoryInsertResDto insert(AttendanceTheoryInsertReqDataDto data);

	AttendanceTheoryUpdateResDto approving(AttendanceTheoryUpdateReqDataDto data);
}
