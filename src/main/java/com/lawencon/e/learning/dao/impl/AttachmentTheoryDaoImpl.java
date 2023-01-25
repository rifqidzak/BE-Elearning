package com.lawencon.e.learning.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.e.learning.dao.AttachmentTheoryDao;
import com.lawencon.e.learning.model.AttachmentTheory;

@Repository
public class AttachmentTheoryDaoImpl extends BaseDaoImpl implements AttachmentTheoryDao {

	@Override
	public AttachmentTheory insert(AttachmentTheory data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public List<AttachmentTheory> getByTheoryId(Long id) {
		final String sql = "SELECT at FROM AttachmentTheory at " + " INNER JOIN at.theoryId t " + " WHERE t.id = :id ";
		List<AttachmentTheory> attachmentTheory = new ArrayList<>();
		attachmentTheory = this.em.createQuery(sql, AttachmentTheory.class).setParameter("id", id).getResultList();
		return attachmentTheory;
	}

}
