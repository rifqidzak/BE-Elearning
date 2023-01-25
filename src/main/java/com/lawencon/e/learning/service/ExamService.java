package com.lawencon.e.learning.service;

import com.lawencon.e.learning.dto.exam.ExamInsertReqDataDto;
import com.lawencon.e.learning.dto.exam.ExamInsertResDto;

public interface ExamService {
	ExamInsertResDto insert(ExamInsertReqDataDto data);

	boolean deleteById(Long id);
}
