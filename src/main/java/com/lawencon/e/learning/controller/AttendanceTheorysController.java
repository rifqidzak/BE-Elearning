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

import com.lawencon.e.learning.dto.attendancetheory.AttendanceTheoryDto;
import com.lawencon.e.learning.dto.attendancetheory.AttendanceTheoryInsertReqDataDto;
import com.lawencon.e.learning.dto.attendancetheory.AttendanceTheoryInsertResDto;
import com.lawencon.e.learning.dto.attendancetheory.AttendanceTheoryUpdateReqDataDto;
import com.lawencon.e.learning.dto.attendancetheory.AttendanceTheoryUpdateResDto;
import com.lawencon.e.learning.dto.attendancetheory.AttendanceTheorysDto;
import com.lawencon.e.learning.service.AttendanceTheoryService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("attendance-theorys")
public class AttendanceTheorysController {
	private final AttendanceTheoryService attendanceTheoryService;

	public AttendanceTheorysController(AttendanceTheoryService attendanceTheoryService) {
		this.attendanceTheoryService = attendanceTheoryService;
	}

	@PostMapping
	public ResponseEntity<AttendanceTheoryInsertResDto> insert(@RequestBody @Valid AttendanceTheoryInsertReqDataDto data) {
		final AttendanceTheoryInsertResDto attendanceTheoryInsertResDto = attendanceTheoryService.insert(data);
		return new ResponseEntity<>(attendanceTheoryInsertResDto, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<AttendanceTheoryUpdateResDto> approving(
			@RequestBody @Valid AttendanceTheoryUpdateReqDataDto data) {
		final AttendanceTheoryUpdateResDto attendanceTheoryUpdateResDto = attendanceTheoryService.approving(data);
		return new ResponseEntity<>(attendanceTheoryUpdateResDto, HttpStatus.OK);
	}

	@GetMapping("student/{study-class-detail-id}")
	public ResponseEntity<AttendanceTheorysDto> getAllbyStudentSide(@PathVariable("study-class-detail-id") Long id) {
		final AttendanceTheorysDto attendanceTheorysDto = attendanceTheoryService.getAllbyStudentSide(id);
		return new ResponseEntity<>(attendanceTheorysDto, HttpStatus.OK);
	}

	@GetMapping("teacher/{study-class-id}")
	public ResponseEntity<AttendanceTheorysDto> getAllbyTeacherSide(@PathVariable("study-class-id") Long id) {
		final AttendanceTheorysDto attendanceTheorysDto = attendanceTheoryService.getAllbyTeacherSide(id);
		return new ResponseEntity<>(attendanceTheorysDto, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<AttendanceTheoryDto> getById(@PathVariable("id") Long id) {
		final AttendanceTheoryDto attendanceTheoryDto = attendanceTheoryService.getById(id);
		return new ResponseEntity<>(attendanceTheoryDto, HttpStatus.OK);
	}
}
