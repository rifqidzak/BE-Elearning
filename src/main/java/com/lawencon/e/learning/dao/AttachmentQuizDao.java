package com.lawencon.e.learning.dao;

import java.util.List;

import com.lawencon.e.learning.model.AttachmentQuiz;

public interface AttachmentQuizDao {
	AttachmentQuiz insert (AttachmentQuiz data);
	
	List<AttachmentQuiz> getByQuizId(Long id);
}
