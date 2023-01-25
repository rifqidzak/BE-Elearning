package com.lawencon.e.learning.service;

import com.lawencon.e.learning.dto.studyclass.StudyClassDeleteResDto;
import com.lawencon.e.learning.dto.studyclass.StudyClassDto;
import com.lawencon.e.learning.dto.studyclass.StudyClassInsertReqDataDto;
import com.lawencon.e.learning.dto.studyclass.StudyClassInsertResDto;
import com.lawencon.e.learning.dto.studyclass.StudyClassUpdateReqDataDto;
import com.lawencon.e.learning.dto.studyclass.StudyClassUpdateResDto;
import com.lawencon.e.learning.dto.studyclass.StudyClassesDto;

public interface StudyClassService {
	StudyClassInsertResDto insert(final StudyClassInsertReqDataDto data);

	StudyClassesDto getAllWithTeacher();

	StudyClassDto getById(final Long id);

	StudyClassUpdateResDto update(final StudyClassUpdateReqDataDto data);

	StudyClassDeleteResDto deleteById(final Long id);

	StudyClassesDto getAllbyTeachersSide(Long id);
}
