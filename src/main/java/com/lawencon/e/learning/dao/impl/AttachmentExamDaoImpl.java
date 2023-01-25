package com.lawencon.e.learning.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.e.learning.dao.AttachmentExamDao;
import com.lawencon.e.learning.model.AttachmentExam;

@Repository
public class AttachmentExamDaoImpl extends BaseDaoImpl implements AttachmentExamDao {

	@Override
	public AttachmentExam insert(AttachmentExam data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public List<AttachmentExam> getByExamId(Long id) {
		final String sql = "SELECT ae FROM AttachmentExam ae " + " INNER JOIN ae.examId e " + " WHERE e.id = :id ";
		List<AttachmentExam> attachmentExams = new ArrayList<>();
		attachmentExams = this.em.createQuery(sql, AttachmentExam.class).setParameter("id", id).getResultList();
		return attachmentExams;
	}

}
