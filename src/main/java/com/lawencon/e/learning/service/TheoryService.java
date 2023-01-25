package com.lawencon.e.learning.service;

import com.lawencon.e.learning.dto.theory.TheoryInsertReqDataDto;
import com.lawencon.e.learning.dto.theory.TheoryInsertResDto;

public interface TheoryService {
	TheoryInsertResDto insert(TheoryInsertReqDataDto data);

	boolean deleteById(Long id);
}
