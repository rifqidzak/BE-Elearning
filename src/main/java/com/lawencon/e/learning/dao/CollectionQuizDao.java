package com.lawencon.e.learning.dao;

import java.util.List;
import java.util.Optional;

import com.lawencon.e.learning.model.CollectionQuiz;

public interface CollectionQuizDao {
	CollectionQuiz insert(CollectionQuiz data);

	Optional<CollectionQuiz> getById(Long id);

	List<CollectionQuiz> getAllByTeacherSide(Long studyClassId);

	CollectionQuiz scoring(CollectionQuiz data);

	Optional<CollectionQuiz> getByScheduleIdAndStudentId(Long scheduleId, Long studentId);
}
