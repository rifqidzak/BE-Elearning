package com.lawencon.e.learning.controller;

import java.util.Base64;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.e.learning.model.File;
import com.lawencon.e.learning.service.FileService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("files")
public class FilesController {
	private final FileService fileService;

	public FilesController(FileService fileService) {
		this.fileService = fileService;
	}

	@GetMapping("download/{id}")
	public ResponseEntity<?> download(@PathVariable("id") Long id) {
		final Optional<File> file = fileService.getById(id);
		final byte[] fileBytes = Base64.getDecoder().decode(file.get().getFileName());
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=attachment." + file.get().getExtension()).body(fileBytes);
	}
}
