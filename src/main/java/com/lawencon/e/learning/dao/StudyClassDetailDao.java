package com.lawencon.e.learning.dao;

import java.util.List;
import java.util.Optional;

import com.lawencon.e.learning.model.StudyClassDetail;

public interface StudyClassDetailDao {
	StudyClassDetail insert(final StudyClassDetail data);

	List<StudyClassDetail> getAllByStudentId(Long id);

	Optional<StudyClassDetail> getById(Long id);
}
