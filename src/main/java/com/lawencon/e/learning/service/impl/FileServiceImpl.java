package com.lawencon.e.learning.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lawencon.e.learning.dao.FileDao;
import com.lawencon.e.learning.model.File;
import com.lawencon.e.learning.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	private final FileDao fileDao;

	public FileServiceImpl(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	@Override
	public Optional<File> getById(Long id) {
		return fileDao.getById(id);
	}

}
