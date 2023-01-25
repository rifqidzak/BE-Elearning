package com.lawencon.e.learning.service;

import com.lawencon.e.learning.dto.collectionexam.CollectionExamDto;
import com.lawencon.e.learning.dto.collectionexam.CollectionExamInsertReqDataDto;
import com.lawencon.e.learning.dto.collectionexam.CollectionExamInsertResDto;
import com.lawencon.e.learning.dto.collectionexam.CollectionExamUpdateReqDataDto;
import com.lawencon.e.learning.dto.collectionexam.CollectionExamUpdateResDto;
import com.lawencon.e.learning.dto.collectionexam.CollectionExamsDto;

public interface CollectionExamService {
	CollectionExamInsertResDto insert(CollectionExamInsertReqDataDto data);

	CollectionExamDto getById(Long id);

	CollectionExamsDto getAllByTeacherSide(Long studyClassId);

	CollectionExamUpdateResDto scoring(CollectionExamUpdateReqDataDto data);

}
