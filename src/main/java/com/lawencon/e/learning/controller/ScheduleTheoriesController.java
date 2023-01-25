package com.lawencon.e.learning.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.e.learning.dto.scheduletheory.ScheduleTheoriesDto;
import com.lawencon.e.learning.dto.scheduletheory.ScheduleTheoryDto;
import com.lawencon.e.learning.service.ScheduleTheoryService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("schedule-theories-controller")
public class ScheduleTheoriesController {
	private final ScheduleTheoryService scheduleTheoryService;

	public ScheduleTheoriesController(ScheduleTheoryService scheduleTheoryService) {
		this.scheduleTheoryService = scheduleTheoryService;
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ScheduleTheoryDto> getById(@PathVariable("id") Long id) {
		final ScheduleTheoryDto scheduleTheoryDto = scheduleTheoryService.getById(id);
		return new ResponseEntity<>(scheduleTheoryDto, HttpStatus.OK);
	}
	
	@GetMapping("teacher/{class-id}")
	public ResponseEntity<ScheduleTheoriesDto> getAllByTeacherSide(@PathVariable("class-id") Long id) {
		final ScheduleTheoriesDto scheduleTheoriesDto = scheduleTheoryService.getAllByTeacherSide(id);
		return new ResponseEntity<>(scheduleTheoriesDto, HttpStatus.OK);
	}
	
	@GetMapping("student/{study-class-id}")
	public ResponseEntity<ScheduleTheoriesDto> getAllByStudentSideForAttendance(@PathVariable("study-class-id") Long id) {
		final ScheduleTheoriesDto scheduleTheoriesDto = scheduleTheoryService.getAllByStudentSideForAttendance(id);
		return new ResponseEntity<>(scheduleTheoriesDto, HttpStatus.OK);
	}
	
}
