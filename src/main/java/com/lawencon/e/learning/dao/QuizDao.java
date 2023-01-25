package com.lawencon.e.learning.dao;

import java.util.Optional;

import com.lawencon.e.learning.model.Quiz;

public interface QuizDao {
	Quiz insert(Quiz data);

	boolean deleteById(Long id);

	Optional<Quiz> getById(Long id);
}
