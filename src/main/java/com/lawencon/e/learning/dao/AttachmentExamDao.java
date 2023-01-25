package com.lawencon.e.learning.dao;

import java.util.List;

import com.lawencon.e.learning.model.AttachmentExam;


public interface AttachmentExamDao {
	AttachmentExam insert (AttachmentExam data);
	
	List<AttachmentExam> getByExamId(Long id);
}
