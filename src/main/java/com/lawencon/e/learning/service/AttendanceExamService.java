package com.lawencon.e.learning.service;

import com.lawencon.e.learning.dto.attendanceexam.AttendanceExamDto;
import com.lawencon.e.learning.dto.attendanceexam.AttendanceExamInsertReqDataDto;
import com.lawencon.e.learning.dto.attendanceexam.AttendanceExamInsertResDto;
import com.lawencon.e.learning.dto.attendanceexam.AttendanceExamUpdateReqDataDto;
import com.lawencon.e.learning.dto.attendanceexam.AttendanceExamUpdateResDto;
import com.lawencon.e.learning.dto.attendanceexam.AttendanceExamsDto;

public interface AttendanceExamService {
	AttendanceExamsDto getAllbyStudentSide(Long studyClassDetailId);

	AttendanceExamsDto getAllbyTeacherSide(Long studyClassId);

	AttendanceExamDto getById(Long id);

	AttendanceExamInsertResDto insert(AttendanceExamInsertReqDataDto data);

	AttendanceExamUpdateResDto approving(AttendanceExamUpdateReqDataDto data);
}
