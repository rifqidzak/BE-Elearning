package com.lawencon.e.learning.service;

import com.lawencon.e.learning.dto.collectionquiz.CollectionQuizDto;
import com.lawencon.e.learning.dto.collectionquiz.CollectionQuizInsertReqDataDto;
import com.lawencon.e.learning.dto.collectionquiz.CollectionQuizInsertResDto;
import com.lawencon.e.learning.dto.collectionquiz.CollectionQuizUpdateReqDataDto;
import com.lawencon.e.learning.dto.collectionquiz.CollectionQuizUpdateResDto;
import com.lawencon.e.learning.dto.collectionquiz.CollectionQuizsDto;

public interface CollectionQuizService {
	CollectionQuizInsertResDto insert(CollectionQuizInsertReqDataDto data);

	CollectionQuizDto getById(Long id);

	CollectionQuizsDto getAllByTeacherSide(Long studyClassId);

	CollectionQuizUpdateResDto scoring(CollectionQuizUpdateReqDataDto data);

}
