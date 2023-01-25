package com.lawencon.e.learning.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.e.learning.dao.CollectionExamDao;
import com.lawencon.e.learning.model.CollectionExam;

@Repository
public class CollectionExamDaoImpl extends BaseDaoImpl implements CollectionExamDao {

	@Override
	public CollectionExam insert(CollectionExam data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public Optional<CollectionExam> getById(Long id) {
		CollectionExam collectionExam = new CollectionExam();
		Optional<CollectionExam> collectionExamOptional = Optional.ofNullable(null);
		try {
			collectionExam = this.em.find(CollectionExam.class, id);
			this.em.detach(collectionExam);
			collectionExamOptional = Optional.ofNullable(collectionExam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return collectionExamOptional;
	}

	@Override
	public List<CollectionExam> getAllByTeacherSide(Long studyClassId) {
		final String sql = "SELECT ce FROM CollectionExam ce " + "	INNER JOIN FETCH ce.userId s"
				+ " INNER JOIN FETCH ce.scheduleId se" + "	INNER JOIN FETCH se.examId e "
				+ " INNER JOIN FETCH e.studyClassId sc" + " WHERE sc.id = :studyClassId ";
		List<CollectionExam> collectionExams = new ArrayList<>();
		collectionExams = this.em.createQuery(sql, CollectionExam.class).setParameter("studyClassId", studyClassId)
				.getResultList();
		return collectionExams;
	}

	@Override
	public CollectionExam scoring(CollectionExam data) {
		final CollectionExam collectionExamUpdated = this.em.merge(data);
		this.em.flush();
		return collectionExamUpdated;
	}

	@Override
	public Optional<CollectionExam> getByScheduleIdAndStudentId(Long scheduleId, Long studentId) {
		final String sql = "SELECT ce FROM CollectionExam ce " + " INNER JOIN FETCH ce.scheduleId se "
				+ "	INNER JOIN FETCH ce.userId u " + " WHERE se.id = :scheduleId AND u.id = :userId";
		Optional<CollectionExam> collectionExamOptional = Optional.ofNullable(null);
		try {
			CollectionExam collectionExam = new CollectionExam();
			this.em.detach(collectionExam);
			collectionExamOptional = Optional.ofNullable(collectionExam);
			collectionExam = this.em.createQuery(sql, CollectionExam.class).setParameter("scheduleId", scheduleId)
					.setParameter("userId", studentId).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return collectionExamOptional;
	}

}
