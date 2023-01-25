package com.lawencon.e.learning.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.e.learning.dto.exam.ExamInsertReqDataDto;
import com.lawencon.e.learning.dto.exam.ExamInsertResDto;
import com.lawencon.e.learning.service.ExamService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("exams")
public class ExamsController {
	private final ExamService examService;

	public ExamsController(ExamService examService) {
		this.examService = examService;
	}

	@PostMapping
	public ResponseEntity<ExamInsertResDto> insert(@RequestBody @Valid ExamInsertReqDataDto data) {
		final ExamInsertResDto examInsertResDto = examService.insert(data);
		return new ResponseEntity<>(examInsertResDto, HttpStatus.CREATED);
	}
}
