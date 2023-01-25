package com.lawencon.e.learning.dao;

import java.util.List;
import java.util.Optional;

import com.lawencon.e.learning.model.AttendanceTheory;

public interface AttendanceTheoryDao {
	
	List<AttendanceTheory>getAllbyStudentSide(Long studyClassDetailId) ;
	
	List<AttendanceTheory>getAllbyTeacherSide(Long studyClassId) ;
		
	Optional<AttendanceTheory> getById(Long id) ;
	
	Optional<AttendanceTheory> getByStudentIdAndScheduleId(Long studentId, Long scheduleId) ;
	
	AttendanceTheory insert(AttendanceTheory data) ;
	
	AttendanceTheory approving(AttendanceTheory data);
}
