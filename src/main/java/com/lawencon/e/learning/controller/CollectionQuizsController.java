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

import com.lawencon.e.learning.dto.collectionquiz.CollectionQuizDto;
import com.lawencon.e.learning.dto.collectionquiz.CollectionQuizInsertReqDataDto;
import com.lawencon.e.learning.dto.collectionquiz.CollectionQuizInsertResDto;
import com.lawencon.e.learning.dto.collectionquiz.CollectionQuizUpdateReqDataDto;
import com.lawencon.e.learning.dto.collectionquiz.CollectionQuizUpdateResDto;
import com.lawencon.e.learning.dto.collectionquiz.CollectionQuizsDto;
import com.lawencon.e.learning.service.CollectionQuizService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("collection-quizs")
public class CollectionQuizsController {
	private final CollectionQuizService collectionQuizService;

	public CollectionQuizsController(CollectionQuizService collectionQuizService) {
		this.collectionQuizService = collectionQuizService;
	}

	@PostMapping
	public ResponseEntity<CollectionQuizInsertResDto> insert(@RequestBody @Valid CollectionQuizInsertReqDataDto data) {
		final CollectionQuizInsertResDto collectionQuizInsertResDto = collectionQuizService.insert(data);
		return new ResponseEntity<>(collectionQuizInsertResDto, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<CollectionQuizUpdateResDto> scoring(@RequestBody @Valid CollectionQuizUpdateReqDataDto data) {
		final CollectionQuizUpdateResDto collectionQuizUpdateResDto = collectionQuizService.scoring(data);
		return new ResponseEntity<>(collectionQuizUpdateResDto, HttpStatus.CREATED);
	}

	@GetMapping("teacher/{study-class-id}")
	public ResponseEntity<CollectionQuizsDto> getAllbyTeacherSide(@PathVariable("study-class-id") Long id) {
		final CollectionQuizsDto collectionQuizsDto = collectionQuizService.getAllByTeacherSide(id);
		return new ResponseEntity<>(collectionQuizsDto, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<CollectionQuizDto> getById(@PathVariable("id") Long id) {
		final CollectionQuizDto collectionQuizDto = collectionQuizService.getById(id);
		return new ResponseEntity<>(collectionQuizDto, HttpStatus.OK);
	}
}
