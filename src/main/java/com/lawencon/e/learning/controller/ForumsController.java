package com.lawencon.e.learning.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.e.learning.dto.forum.ForumDto;
import com.lawencon.e.learning.dto.forum.ForumInsertReqDataDto;
import com.lawencon.e.learning.dto.forum.ForumInsertResDto;
import com.lawencon.e.learning.dto.forum.ForumsDto;
import com.lawencon.e.learning.service.ForumService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("forums")
public class ForumsController {
	private final ForumService forumService;

	public ForumsController(ForumService forumService) {
		this.forumService = forumService;
	}

	@PostMapping
	public ResponseEntity<ForumInsertResDto> insert(@RequestBody @Valid ForumInsertReqDataDto data) {
		final ForumInsertResDto forumInsertResDto = forumService.insert(data);
		return new ResponseEntity<>(forumInsertResDto, HttpStatus.CREATED);
	}

	@GetMapping("teacher/{study-class-id}")
	public ResponseEntity<ForumsDto> getAllbyTeacherSide(@PathVariable("study-class-id") Long id) {
		final ForumsDto forumsDto = forumService.getAllByTeacherSide(id);
		return new ResponseEntity<>(forumsDto, HttpStatus.OK);
	}

	@GetMapping("student/{study-class-detail-id}")
	public ResponseEntity<ForumsDto> getAllbyStudentSide(@PathVariable("study-class-detail-id") Long id) {
		final ForumsDto forumsDto = forumService.getAllByStudentSide(id);
		return new ResponseEntity<>(forumsDto, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<ForumDto> getById(@PathVariable("id") Long id) {
		final ForumDto forumDto = forumService.getById(id);
		return new ResponseEntity<>(forumDto, HttpStatus.OK);
	}
}
