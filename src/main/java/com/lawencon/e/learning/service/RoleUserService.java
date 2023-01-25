package com.lawencon.e.learning.service;

import com.lawencon.e.learning.dto.role.RoleDto;
import com.lawencon.e.learning.dto.role.RolesDto;

public interface RoleUserService {
	RolesDto getAll();

	RoleDto getById(final Long id);

	RoleDto getByCode(final String code);

}
