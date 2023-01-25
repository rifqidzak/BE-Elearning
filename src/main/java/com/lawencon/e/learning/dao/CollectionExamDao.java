package com.lawencon.e.learning.dao;

import java.util.List;
import java.util.Optional;

import com.lawencon.e.learning.model.CollectionExam;

public interface CollectionExamDao {
	CollectionExam insert(CollectionExam data);

	Optional<CollectionExam> getById(Long id);

	List<CollectionExam> getAllByTeacherSide(Long studyClassId);

	CollectionExam scoring(CollectionExam data);

	Optional<CollectionExam> getByScheduleIdAndStudentId(Long scheduleId, Long studentId);
}
