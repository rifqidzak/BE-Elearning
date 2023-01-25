package com.lawencon.e.learning.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.e.learning.dao.StudyClassDao;
import com.lawencon.e.learning.model.StudyClass;

@Repository
public class StudyClassDaoImpl extends BaseDaoImpl implements StudyClassDao {

	@Override
	public StudyClass insert(StudyClass data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public StudyClass update(StudyClass data) {
		final StudyClass studyClassUpdated = this.em.merge(data);

		return studyClassUpdated;
	}

	@Override
	public List<StudyClass> getAll() {
		final String sql = "SELECT sc FROM StudyClass sc " + " LEFT JOIN FETCH sc.photoClass";
		final List<StudyClass> studyClass = this.em.createQuery(sql, StudyClass.class).getResultList();
		return studyClass;
	}

	@Override
	public Optional<StudyClass> getById(Long id) {
		StudyClass studyClass = new StudyClass();
		Optional<StudyClass> studyClassOptional = Optional.ofNullable(null);
		try {
			studyClass = this.em.find(StudyClass.class, id);
			this.em.detach(studyClass);
			studyClassOptional = Optional.ofNullable(studyClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studyClassOptional;
	}

	@Override
	public boolean deleteById(Long id) {
		final String sql = " DELETE FROM StudyClass WHERE id = :id ";

		final int result = this.em.createQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}

	@Override
	public List<StudyClass> getAllWithTeacher() {
		final String sql = "SELECT sc FROM StudyClass sc " + " INNER JOIN FETCH sc.teacherId "
				+ " LEFT JOIN FETCH sc.photoClass ";
		final List<StudyClass> studyClass = this.em.createQuery(sql, StudyClass.class).getResultList();
		return studyClass;
	}

	@Override
	public List<StudyClass> getAllbyTeachersSide(Long id) {
		final String sql = "SELECT sc FROM StudyClass sc " + " INNER JOIN FETCH sc.teacherId tId "
				+ " LEFT JOIN FETCH sc.photoClass " + " WHERE tId.id = :id";
		List<StudyClass> studyClass = new ArrayList<>();
		studyClass = this.em.createQuery(sql, StudyClass.class).setParameter("id", id).getResultList();
		return studyClass;
	}

}
