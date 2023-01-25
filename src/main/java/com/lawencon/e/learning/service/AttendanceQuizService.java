package com.lawencon.e.learning.service;

import com.lawencon.e.learning.dto.attendancequiz.AttendanceQuizDto;
import com.lawencon.e.learning.dto.attendancequiz.AttendanceQuizInsertReqDataDto;
import com.lawencon.e.learning.dto.attendancequiz.AttendanceQuizInsertResDto;
import com.lawencon.e.learning.dto.attendancequiz.AttendanceQuizUpdateReqDataDto;
import com.lawencon.e.learning.dto.attendancequiz.AttendanceQuizUpdateResDto;
import com.lawencon.e.learning.dto.attendancequiz.AttendanceQuizsDto;

public interface AttendanceQuizService {
	AttendanceQuizsDto getAllbyStudentSide(Long studyClassDetailId);

	AttendanceQuizsDto getAllbyTeacherSide(Long studyClassId);

	AttendanceQuizDto getById(Long id);

	AttendanceQuizInsertResDto insert(AttendanceQuizInsertReqDataDto data);

	AttendanceQuizUpdateResDto approving(AttendanceQuizUpdateReqDataDto data);
}
