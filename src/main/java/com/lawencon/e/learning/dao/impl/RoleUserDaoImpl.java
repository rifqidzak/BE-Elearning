package com.lawencon.e.learning.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.e.learning.dao.RoleUserDao;
import com.lawencon.e.learning.model.RoleUser;

@Repository
public class RoleUserDaoImpl extends BaseDaoImpl implements RoleUserDao {

	@Override
	public List<RoleUser> getAll() {
		final String sql = "SELECT ru FROM RoleUser ru";
		final List<RoleUser> roleUser = this.em.createQuery(sql, RoleUser.class).getResultList();
		return roleUser;
	}

	@Override
	public Optional<RoleUser> getById(Long id) {
		RoleUser roleUser = new RoleUser();
		Optional<RoleUser> roleUserOptional = Optional.ofNullable(null);
		try {
			roleUser = this.em.find(RoleUser.class, id);
			this.em.detach(roleUser);
			roleUserOptional = Optional.ofNullable(roleUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roleUserOptional;
	}


	@Override
	public Optional<RoleUser> getByCode(String code) {
		final String sql = "SELECT ru FROM RoleUser ru WHERE ru.roleUserCode = :code";
		Optional<RoleUser> roleUserOptional = null;
		try {
			RoleUser roleUser = this.em.createQuery(sql, RoleUser.class).setParameter("code", code).getSingleResult();
			this.em.detach(roleUser);
			roleUserOptional = Optional.ofNullable(roleUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roleUserOptional;
	}

}
