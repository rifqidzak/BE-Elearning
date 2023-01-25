package com.lawencon.e.learning.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.e.learning.dto.scheduleexam.ScheduleExamDto;
import com.lawencon.e.learning.dto.scheduleexam.ScheduleExamsDto;
import com.lawencon.e.learning.service.ScheduleExamService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("schedule-exams-controller")
public class ScheduleExamsController {
	private final ScheduleExamService scheduleExamService;

	public ScheduleExamsController(ScheduleExamService scheduleExamService) {
		this.scheduleExamService = scheduleExamService;
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ScheduleExamDto> getById(@PathVariable("id") Long id) {
		final ScheduleExamDto scheduleExamDto = scheduleExamService.getById(id);
		return new ResponseEntity<>(scheduleExamDto, HttpStatus.OK);
	}
	
	@GetMapping("teacher/{class-id}")
	public ResponseEntity<ScheduleExamsDto> getAllByTeacherSide(@PathVariable("class-id") Long id) {
		final ScheduleExamsDto scheduleExamsDto = scheduleExamService.getAllByTeacherSide(id);
		return new ResponseEntity<>(scheduleExamsDto, HttpStatus.OK);
	}
	
	@GetMapping("student/{study-class-id}")
	public ResponseEntity<ScheduleExamsDto> getAllByStudentSideForAttendance(@PathVariable("study-class-id") Long id) {
		final ScheduleExamsDto scheduleExamsDto = scheduleExamService.getAllByStudentSideForAttendance(id);
		return new ResponseEntity<>(scheduleExamsDto, HttpStatus.OK);
	}
	
}
