package com.lawencon.e.learning.dao.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.e.learning.dao.TheoryDao;
import com.lawencon.e.learning.model.Theory;

@Repository
public class TheoryDaoImpl extends BaseDaoImpl implements TheoryDao {

	@Override
	public Theory insert(Theory data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public boolean deleteById(Long id) {
		final String sql = " DELETE FROM Theory WHERE id = :id ";

		final int result = this.em.createQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}

	@Override
	public Optional<Theory> getById(Long id) {
		Theory theory = new Theory();
		Optional<Theory> theoryOptional = Optional.ofNullable(null);
		try {
			theory = this.em.find(Theory.class, id);
			this.em.detach(theory);
			theoryOptional = Optional.ofNullable(theory);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return theoryOptional;
	}

}
