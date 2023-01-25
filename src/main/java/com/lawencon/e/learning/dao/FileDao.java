package com.lawencon.e.learning.dao;

import java.util.Optional;

import com.lawencon.e.learning.model.File;

public interface FileDao {
	File insert(File data);

	Optional<File> getById(Long id);
}
