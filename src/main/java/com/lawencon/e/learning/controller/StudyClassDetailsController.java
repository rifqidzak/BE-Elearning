package com.lawencon.e.learning.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.e.learning.dto.studyclassdetail.StudyClassDetailDto;
import com.lawencon.e.learning.dto.studyclassdetail.StudyClassDetailInsertReqDataDto;
import com.lawencon.e.learning.dto.studyclassdetail.StudyClassDetailInsertResDto;
import com.lawencon.e.learning.dto.studyclassdetail.StudyClassesDetailDto;
import com.lawencon.e.learning.service.StudyClassDetailService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("study-class-details")
public class StudyClassDetailsController {
	private final StudyClassDetailService studyClassDetailService;

	public StudyClassDetailsController(StudyClassDetailService studyClassDetailService) {
		this.studyClassDetailService = studyClassDetailService;
	}

	@PostMapping
	public ResponseEntity<StudyClassDetailInsertResDto> insert(
			@RequestBody @Valid StudyClassDetailInsertReqDataDto data) {
		final StudyClassDetailInsertResDto studyClassInsertResDto = studyClassDetailService.insert(data);
		return new ResponseEntity<>(studyClassInsertResDto, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<StudyClassesDetailDto> getAllByStudentId(@RequestParam Long id) {
		final StudyClassesDetailDto studyClassesDetailDto = studyClassDetailService.getAllByStudentId(id);
		return new ResponseEntity<>(studyClassesDetailDto, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<StudyClassDetailDto> getById(@PathVariable Long id) {
		final StudyClassDetailDto studyClassDetailDto = studyClassDetailService.getById(id);
		return new ResponseEntity<>(studyClassDetailDto, HttpStatus.OK);
	}
}
