package com.lawencon.e.learning.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.e.learning.dao.UserDao;
import com.lawencon.e.learning.model.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	@Override
	public List<User> getAll() {
		final String sql = "SELECT u FROM User u INNER JOIN FETCH u.roleUserId " + " LEFT JOIN FETCH u.photoUser ";
		final List<User> users = this.em.createQuery(sql, User.class).getResultList();
		return users;
	}

	@Override
	public boolean deleteById(Long id) {
		final String sql = " DELETE FROM User WHERE id = :id ";

		final int result = this.em.createQuery(sql).setParameter("id", id).executeUpdate();

		return result > 0;
	}

	@Override
	public User insert(User data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public User update(User data) {
		final User userUpdated = this.em.merge(data);
		this.em.flush();

		return userUpdated;
	}

	@Override
	public Optional<User> getById(Long id) {
		User user = new User();
		Optional<User> userOptional = Optional.ofNullable(null);
		try {
			user = this.em.find(User.class, id);
			this.em.detach(user);
			userOptional = Optional.ofNullable(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userOptional;
	}

	@Override
	public Optional<User> getByEmail(String email) {
		final String sql = "SELECT u FROM User u" + " INNER JOIN FETCH u.roleUserId " + " LEFT JOIN FETCH u.photoUser "
				+ " WHERE u.email = :email AND u.isActive = true";
		Optional<User> userOptional = Optional.ofNullable(null);
		try {
			User user = this.em.createQuery(sql, User.class).setParameter("email", email).getSingleResult();
			this.em.detach(user);
			userOptional = Optional.ofNullable(user);
			return userOptional;
		} catch (Exception e) {
			e.printStackTrace();
			return userOptional;
		}
	}

	@Override
	public List<User> getAllByRole(String roleCode) {
		final String sql = "SELECT u FROM User u " + " INNER JOIN FETCH u.roleUserId " + " LEFT JOIN FETCH u.photoUser "
				+ "WHERE u.roleUserId.roleUserCode = :roleCode";
		final List<User> users = this.em.createQuery(sql, User.class).setParameter("roleCode", roleCode)
				.getResultList();
		return users;
	}

}
