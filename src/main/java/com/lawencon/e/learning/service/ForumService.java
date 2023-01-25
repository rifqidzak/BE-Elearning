package com.lawencon.e.learning.service;

import com.lawencon.e.learning.dto.forum.ForumDto;
import com.lawencon.e.learning.dto.forum.ForumInsertReqDataDto;
import com.lawencon.e.learning.dto.forum.ForumInsertResDto;
import com.lawencon.e.learning.dto.forum.ForumsDto;

public interface ForumService {
	ForumsDto getAllByTeacherSide(Long studyClassId);

	ForumsDto getAllByStudentSide(Long studyClassDetailId);

	ForumDto getById(Long id);

	ForumInsertResDto insert(ForumInsertReqDataDto data);
}
