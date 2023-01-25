package com.lawencon.e.learning.service;

import com.lawencon.e.learning.dto.studyclassdetail.StudyClassDetailDto;
import com.lawencon.e.learning.dto.studyclassdetail.StudyClassDetailInsertReqDataDto;
import com.lawencon.e.learning.dto.studyclassdetail.StudyClassDetailInsertResDto;
import com.lawencon.e.learning.dto.studyclassdetail.StudyClassesDetailDto;

public interface StudyClassDetailService {
	StudyClassDetailInsertResDto insert(final StudyClassDetailInsertReqDataDto data);

	StudyClassesDetailDto getAllByStudentId(Long id);

	StudyClassDetailDto getById(Long id);

}
