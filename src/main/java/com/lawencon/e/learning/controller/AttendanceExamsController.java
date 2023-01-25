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

import com.lawencon.e.learning.dto.attendanceexam.AttendanceExamDto;
import com.lawencon.e.learning.dto.attendanceexam.AttendanceExamInsertReqDataDto;
import com.lawencon.e.learning.dto.attendanceexam.AttendanceExamInsertResDto;
import com.lawencon.e.learning.dto.attendanceexam.AttendanceExamUpdateReqDataDto;
import com.lawencon.e.learning.dto.attendanceexam.AttendanceExamUpdateResDto;
import com.lawencon.e.learning.dto.attendanceexam.AttendanceExamsDto;
import com.lawencon.e.learning.service.AttendanceExamService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("attendance-exams")
public class AttendanceExamsController {
	private final AttendanceExamService attendanceExamService;

	public AttendanceExamsController(AttendanceExamService attendanceExamService) {
		this.attendanceExamService = attendanceExamService;
	}
	
	@PostMapping
	public ResponseEntity<AttendanceExamInsertResDto> insert(@RequestBody @Valid AttendanceExamInsertReqDataDto data) {
		final AttendanceExamInsertResDto attendanceExamInsertResDto = attendanceExamService.insert(data);
		return new ResponseEntity<>(attendanceExamInsertResDto, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<AttendanceExamUpdateResDto> approving(@RequestBody @Valid AttendanceExamUpdateReqDataDto data) {
		final AttendanceExamUpdateResDto attendanceExamUpdateResDto = attendanceExamService.approving(data);
		return new ResponseEntity<>(attendanceExamUpdateResDto, HttpStatus.OK);
	}
	
	@GetMapping("student/{study-class-detail-id}")
	public ResponseEntity<AttendanceExamsDto> getAllbyStudentSide(@PathVariable("study-class-detail-id") Long id) {
		final AttendanceExamsDto attendanceExamsDto = attendanceExamService.getAllbyStudentSide(id);
		return new ResponseEntity<>(attendanceExamsDto, HttpStatus.OK);
	}
	
	@GetMapping("teacher/{study-class-id}")
	public ResponseEntity<AttendanceExamsDto> getAllbyTeacherSide(@PathVariable("study-class-id") Long id) {
		final AttendanceExamsDto attendanceExamsDto = attendanceExamService.getAllbyTeacherSide(id);
		return new ResponseEntity<>(attendanceExamsDto, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<AttendanceExamDto> getById(@PathVariable("id") Long id) {
		final AttendanceExamDto attendanceExamDto = attendanceExamService.getById(id);
		return new ResponseEntity<>(attendanceExamDto, HttpStatus.OK);
	}
}
