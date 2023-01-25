package com.lawencon.e.learning.dao;

import java.util.List;
import java.util.Optional;

import com.lawencon.e.learning.model.StudyClass;

public interface StudyClassDao extends BaseMasterDao<StudyClass> {
	List<StudyClass> getAllWithTeacher();

	List<StudyClass> getAllbyTeachersSide(Long id);

	Optional<StudyClass> getById(Long id);
}
