package com.lawencon.e.learning.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.e.learning.dto.quiz.QuizInsertReqDataDto;
import com.lawencon.e.learning.dto.quiz.QuizInsertResDto;
import com.lawencon.e.learning.service.QuizService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("quizs")
public class QuizsController {
	private final QuizService quizService;

	public QuizsController(QuizService quizService) {
		this.quizService = quizService;
	}

	@PostMapping
	public ResponseEntity<QuizInsertResDto> insert(@RequestBody @Valid QuizInsertReqDataDto data) {
		final QuizInsertResDto quizInsertResDto = quizService.insert(data);
		return new ResponseEntity<>(quizInsertResDto, HttpStatus.CREATED);
	}
}
