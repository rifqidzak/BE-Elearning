package com.lawencon.e.learning.dao;

import java.util.Optional;

import com.lawencon.e.learning.model.Exam;

public interface ExamDao {
	Exam insert(Exam data);

	boolean delete(Long id);

	Optional<Exam> getById(Long id);
}
