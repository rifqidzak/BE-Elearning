package com.lawencon.e.learning.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.e.learning.dto.attendancequiz.AttendanceQuizDto;
import com.lawencon.e.learning.dto.attendancequiz.AttendanceQuizInsertReqDataDto;
import com.lawencon.e.learning.dto.attendancequiz.AttendanceQuizInsertResDto;
import com.lawencon.e.learning.dto.attendancequiz.AttendanceQuizUpdateReqDataDto;
import com.lawencon.e.learning.dto.attendancequiz.AttendanceQuizUpdateResDto;
import com.lawencon.e.learning.dto.attendancequiz.AttendanceQuizsDto;
import com.lawencon.e.learning.service.AttendanceQuizService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("attendance-quizs")
public class AttendanceQuizsController {
	private final AttendanceQuizService attendanceQuizService;

	public AttendanceQuizsController(AttendanceQuizService attendanceQuizService) {
		this.attendanceQuizService = attendanceQuizService;
	}

	@PostMapping
	public ResponseEntity<AttendanceQuizInsertResDto> insert(@RequestBody @Valid AttendanceQuizInsertReqDataDto data) {
		final AttendanceQuizInsertResDto attendanceQuizInsertResDto = attendanceQuizService.insert(data);
		return new ResponseEntity<>(attendanceQuizInsertResDto, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<AttendanceQuizUpdateResDto> approving(
			@RequestBody @Valid AttendanceQuizUpdateReqDataDto data) {
		final AttendanceQuizUpdateResDto attendanceQuizUpdateResDto = attendanceQuizService.approving(data);
		return new ResponseEntity<>(attendanceQuizUpdateResDto, HttpStatus.OK);
	}

	@GetMapping("student/{study-class-detail-id}")
	public ResponseEntity<AttendanceQuizsDto> getAllbyStudentSide(@PathVariable("study-class-detail-id") Long id) {
		final AttendanceQuizsDto attendanceQuizsDto = attendanceQuizService.getAllbyStudentSide(id);
		return new ResponseEntity<>(attendanceQuizsDto, HttpStatus.OK);
	}

	@GetMapping("teacher/{study-class-id}")
	public ResponseEntity<AttendanceQuizsDto> getAllbyTeacherSide(@PathVariable("study-class-id") Long id) {
		final AttendanceQuizsDto attendanceQuizsDto = attendanceQuizService.getAllbyTeacherSide(id);
		return new ResponseEntity<>(attendanceQuizsDto, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<AttendanceQuizDto> getById(@PathVariable("id") Long id) {
		final AttendanceQuizDto attendanceQuizDto = attendanceQuizService.getById(id);
		return new ResponseEntity<>(attendanceQuizDto, HttpStatus.OK);
	}
}
