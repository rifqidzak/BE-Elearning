package com.lawencon.e.learning.dao.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.e.learning.dao.ExamDao;
import com.lawencon.e.learning.model.Exam;
@Repository
public class ExamDaoImpl extends BaseDaoImpl implements ExamDao {

	@Override
	public Exam insert(Exam data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public boolean delete(Long id) {
		final String sql = " DELETE FROM Theory WHERE id = :id ";

		final int result = this.em.createQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}

	@Override
	public Optional<Exam> getById(Long id) {
		Exam exam = new Exam();
		Optional<Exam> examOptional = Optional.ofNullable(null);
		try {
			exam = this.em.find(Exam.class, id);
			this.em.detach(exam);
			examOptional = Optional.ofNullable(exam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return examOptional;
	}

}
