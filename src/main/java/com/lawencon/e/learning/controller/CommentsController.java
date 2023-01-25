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

import com.lawencon.e.learning.dto.comment.CommentInsertReqDataDto;
import com.lawencon.e.learning.dto.comment.CommentInsertResDto;
import com.lawencon.e.learning.dto.comment.CommentsDto;
import com.lawencon.e.learning.service.CommentService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("comments")
public class CommentsController {
	private final CommentService commentService;

	public CommentsController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping
	public ResponseEntity<CommentInsertResDto> insert(@RequestBody @Valid CommentInsertReqDataDto data) {
		final CommentInsertResDto commentInsertResDto = commentService.insert(data);
		return new ResponseEntity<>(commentInsertResDto, HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	public ResponseEntity<CommentsDto> getAllByForumId(@PathVariable("id") Long id) {
		final CommentsDto commentsDto = commentService.getAll(id);
		return new ResponseEntity<>(commentsDto, HttpStatus.OK);
	}
}
