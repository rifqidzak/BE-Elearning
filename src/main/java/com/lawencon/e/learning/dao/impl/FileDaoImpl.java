package com.lawencon.e.learning.dao.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.e.learning.dao.FileDao;
import com.lawencon.e.learning.model.File;
@Repository
public class FileDaoImpl extends BaseDaoImpl implements FileDao {

	@Override
	public File insert(File data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public Optional<File> getById(Long id) {
		File file = new File();
		Optional<File> fileOptional = Optional.ofNullable(null);
		try {
			file = this.em.find(File.class, id);
			fileOptional = Optional.ofNullable(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileOptional;
	}

}
