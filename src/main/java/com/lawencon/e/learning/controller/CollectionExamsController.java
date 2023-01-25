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

import com.lawencon.e.learning.dto.collectionexam.CollectionExamDto;
import com.lawencon.e.learning.dto.collectionexam.CollectionExamInsertReqDataDto;
import com.lawencon.e.learning.dto.collectionexam.CollectionExamInsertResDto;
import com.lawencon.e.learning.dto.collectionexam.CollectionExamUpdateReqDataDto;
import com.lawencon.e.learning.dto.collectionexam.CollectionExamUpdateResDto;
import com.lawencon.e.learning.dto.collectionexam.CollectionExamsDto;
import com.lawencon.e.learning.service.CollectionExamService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("collection-exams")
public class CollectionExamsController {
	private final CollectionExamService collectionExamService;

	public CollectionExamsController(CollectionExamService collectionExamService) {
		this.collectionExamService = collectionExamService;
	}

	@PostMapping
	public ResponseEntity<CollectionExamInsertResDto> insert(@RequestBody @Valid CollectionExamInsertReqDataDto data) {
		final CollectionExamInsertResDto collectionExamInsertResDto = collectionExamService.insert(data);
		return new ResponseEntity<>(collectionExamInsertResDto, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<CollectionExamUpdateResDto> scoring(@RequestBody @Valid CollectionExamUpdateReqDataDto data) {
		final CollectionExamUpdateResDto collectionExamUpdateResDto = collectionExamService.scoring(data);
		return new ResponseEntity<>(collectionExamUpdateResDto, HttpStatus.CREATED);
	}
	
	@GetMapping("teacher/{study-class-id}")
	public ResponseEntity<CollectionExamsDto> getAllbyTeacherSide(@PathVariable("study-class-id") Long id) {
		final CollectionExamsDto collectionExamsDto = collectionExamService.getAllByTeacherSide(id);
		return new ResponseEntity<>(collectionExamsDto, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<CollectionExamDto> getById(@PathVariable("id") Long id) {
		final CollectionExamDto collectionExamDto = collectionExamService.getById(id);
		return new ResponseEntity<>(collectionExamDto, HttpStatus.OK);
	}
}
