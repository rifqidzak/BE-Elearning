package com.lawencon.e.learning.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.lawencon.e.learning.model.User;

public interface UserDao extends BaseMasterDao<User> {
	Optional<User> getByEmail(String email);

	List<User> getAllByRole(final String roleCode) throws SQLException;
}
