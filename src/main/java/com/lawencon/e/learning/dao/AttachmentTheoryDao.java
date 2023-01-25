package com.lawencon.e.learning.dao;

import java.util.List;

import com.lawencon.e.learning.model.AttachmentTheory;

public interface AttachmentTheoryDao {
	AttachmentTheory insert(AttachmentTheory data);

	List<AttachmentTheory> getByTheoryId(Long id);
}
