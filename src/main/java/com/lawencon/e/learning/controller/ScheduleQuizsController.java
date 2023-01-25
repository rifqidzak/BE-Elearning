package com.lawencon.e.learning.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.e.learning.dto.schedulequiz.ScheduleQuizDto;
import com.lawencon.e.learning.dto.schedulequiz.ScheduleQuizsDto;
import com.lawencon.e.learning.service.ScheduleQuizService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("schedule-quizs-controller")
public class ScheduleQuizsController {
	private final ScheduleQuizService scheduleQuizService;

	public ScheduleQuizsController(ScheduleQuizService scheduleQuizService) {
		this.scheduleQuizService = scheduleQuizService;
	}

	@GetMapping("{id}")
	public ResponseEntity<ScheduleQuizDto> getById(@PathVariable("id") Long id) {
		final ScheduleQuizDto scheduleQuizDto = scheduleQuizService.getById(id);
		return new ResponseEntity<>(scheduleQuizDto, HttpStatus.OK);
	}

	@GetMapping("teacher/{class-id}")
	public ResponseEntity<ScheduleQuizsDto> getAllByTeacherSide(@PathVariable("class-id") Long id) {
		final ScheduleQuizsDto scheduleQuizsDto = scheduleQuizService.getAllByTeacherSide(id);
		return new ResponseEntity<>(scheduleQuizsDto, HttpStatus.OK);
	}

	@GetMapping("student/{study-class-id}")
	public ResponseEntity<ScheduleQuizsDto> getAllByStudentSideForAttendance(@PathVariable("study-class-id") Long id) {
		final ScheduleQuizsDto scheduleQuizsDto = scheduleQuizService.getAllByStudentSideForAttendance(id);
		return new ResponseEntity<>(scheduleQuizsDto, HttpStatus.OK);
	}
}
