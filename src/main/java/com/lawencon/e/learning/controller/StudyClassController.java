package com.lawencon.e.learning.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.e.learning.dto.studyclass.StudyClassDeleteResDto;
import com.lawencon.e.learning.dto.studyclass.StudyClassDto;
import com.lawencon.e.learning.dto.studyclass.StudyClassInsertReqDataDto;
import com.lawencon.e.learning.dto.studyclass.StudyClassInsertResDto;
import com.lawencon.e.learning.dto.studyclass.StudyClassUpdateReqDataDto;
import com.lawencon.e.learning.dto.studyclass.StudyClassUpdateResDto;
import com.lawencon.e.learning.dto.studyclass.StudyClassesDto;
import com.lawencon.e.learning.service.StudyClassService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("study-class")
public class StudyClassController {
	private final StudyClassService studyClassService;
	
	
	public StudyClassController(StudyClassService studyClassService) {
		this.studyClassService = studyClassService;
	}

	@PostMapping
	public ResponseEntity<StudyClassInsertResDto> insert(@RequestBody @Valid StudyClassInsertReqDataDto data) {
		final StudyClassInsertResDto studyClassInsertResDto = studyClassService.insert(data);
		return new ResponseEntity<>(studyClassInsertResDto, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<StudyClassUpdateResDto> update(@RequestBody @Valid StudyClassUpdateReqDataDto data) {
		final StudyClassUpdateResDto studyClassUpdateResDto = studyClassService.update(data);
		return new ResponseEntity<>(studyClassUpdateResDto, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<StudyClassDto> getById(@PathVariable Long id) {
		final StudyClassDto studyClassDto = studyClassService.getById(id);
		return new ResponseEntity<>(studyClassDto, HttpStatus.OK);
	}
	
	@GetMapping("ts")
	public ResponseEntity<StudyClassesDto> getByTeacherSide(@RequestParam Long id) {
		final StudyClassesDto studyClassesDto = studyClassService.getAllbyTeachersSide(id);
		return new ResponseEntity<>(studyClassesDto, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<StudyClassesDto> getAll() {
		final StudyClassesDto studyClassesDto = studyClassService.getAllWithTeacher();
		return new ResponseEntity<>(studyClassesDto, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<StudyClassDeleteResDto> delete(@PathVariable("id") Long id) {
		final StudyClassDeleteResDto studyClassDeleteResDto = studyClassService.deleteById(id);
		return new ResponseEntity<>(studyClassDeleteResDto, HttpStatus.OK);
	}
}
