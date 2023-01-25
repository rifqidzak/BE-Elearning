package com.lawencon.e.learning.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.e.learning.dao.StudyClassDetailDao;
import com.lawencon.e.learning.model.StudyClassDetail;

@Repository
public class StudyClassDetailDaoImpl extends BaseDaoImpl implements StudyClassDetailDao {

	@Override
	public StudyClassDetail insert(StudyClassDetail data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public List<StudyClassDetail> getAllByStudentId(Long id) {
		final String sql = "SELECT scd FROM StudyClassDetail scd " + " INNER JOIN FETCH scd.studyClassId sc"
				+ " INNER JOIN FETCH scd.studentId " + "	INNER JOIN FETCH sc.teacherId"
				+ " WHERE scd.studentId.id = :id";
		final List<StudyClassDetail> studyClassDetails = this.em.createQuery(sql, StudyClassDetail.class)
				.setParameter("id", id).getResultList();
		return studyClassDetails;
	}

	@Override
	public Optional<StudyClassDetail> getById(Long id) {
		StudyClassDetail studyClassDetail = new StudyClassDetail();
		Optional<StudyClassDetail> studyClassDetailOptional = Optional.ofNullable(null);
		try {
			studyClassDetail = this.em.find(StudyClassDetail.class, id);
			this.em.detach(studyClassDetail);
			studyClassDetailOptional = Optional.ofNullable(studyClassDetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studyClassDetailOptional;
	}

}
