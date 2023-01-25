package com.lawencon.e.learning.service;

import com.lawencon.e.learning.dto.quiz.QuizInsertReqDataDto;
import com.lawencon.e.learning.dto.quiz.QuizInsertResDto;

public interface QuizService {
	QuizInsertResDto insert(QuizInsertReqDataDto data);

	boolean deleteById(Long id);
}
