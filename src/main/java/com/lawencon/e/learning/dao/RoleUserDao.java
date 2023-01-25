package com.lawencon.e.learning.dao;

import java.util.List;
import java.util.Optional;

import com.lawencon.e.learning.model.RoleUser;

public interface RoleUserDao {
	Optional<RoleUser> getByCode(String code);

	List<RoleUser> getAll();

	Optional<RoleUser> getById(Long id);
}
