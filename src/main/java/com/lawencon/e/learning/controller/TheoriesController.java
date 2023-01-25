package com.lawencon.e.learning.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.e.learning.dto.theory.TheoryInsertReqDataDto;
import com.lawencon.e.learning.dto.theory.TheoryInsertResDto;
import com.lawencon.e.learning.service.TheoryService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("theories")
public class TheoriesController {
	private final TheoryService theoryService;

	public TheoriesController(TheoryService theoryService) {
		this.theoryService = theoryService;
	}

	@PostMapping
	public ResponseEntity<TheoryInsertResDto> insert(@RequestBody @Valid TheoryInsertReqDataDto data) {
		final TheoryInsertResDto theoryInsertResDto = theoryService.insert(data);
		return new ResponseEntity<>(theoryInsertResDto, HttpStatus.CREATED);
	}
}
