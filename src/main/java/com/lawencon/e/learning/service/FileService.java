package com.lawencon.e.learning.service;

import java.util.Optional;

import com.lawencon.e.learning.model.File;

public interface FileService {
	Optional<File> getById(Long id);
}
