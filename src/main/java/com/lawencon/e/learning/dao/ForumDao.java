package com.lawencon.e.learning.dao;

import java.util.List;
import java.util.Optional;

import com.lawencon.e.learning.model.Forum;

public interface ForumDao {
	List<Forum> getAllByTeacherSide(Long studyClassId);

	List<Forum> getAllByStudentSide(Long studyClassDetailId);

	Optional<Forum> getById(Long id);

	Forum insert(Forum data);

}
