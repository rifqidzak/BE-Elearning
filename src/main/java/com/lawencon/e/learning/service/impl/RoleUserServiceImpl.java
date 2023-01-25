package com.lawencon.e.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lawencon.e.learning.dao.RoleUserDao;
import com.lawencon.e.learning.dto.role.RoleDataDto;
import com.lawencon.e.learning.dto.role.RoleDto;
import com.lawencon.e.learning.dto.role.RolesDto;
import com.lawencon.e.learning.model.RoleUser;
import com.lawencon.e.learning.service.RoleUserService;

@Service
public class RoleUserServiceImpl implements RoleUserService {

	private final RoleUserDao roleUserDao;

	public RoleUserServiceImpl(RoleUserDao roleUserDao) {
		this.roleUserDao = roleUserDao;
	}

	@Override
	public RolesDto getAll() {
		final List<RoleUser> roleUsers = roleUserDao.getAll();
		final List<RoleDataDto> roleDataDtos = new ArrayList<>();

		for (int i = 0; i < roleUsers.size(); i++) {
			final RoleDataDto roleDataDto = new RoleDataDto();
			roleDataDto.setId(roleUsers.get(i).getId());
			roleDataDto.setRoleUserCode(roleUsers.get(i).getRoleUserCode());
			roleDataDto.setRoleUserName(roleUsers.get(i).getRoleUserName());
			roleDataDto.setVer(roleUsers.get(i).getVer());

			roleDataDtos.add(roleDataDto);
		}
		RolesDto rolesDto = new RolesDto();
		rolesDto.setData(roleDataDtos);
		return rolesDto;
	}

	@Override
	public RoleDto getById(Long id) {
		final Optional<RoleUser> roleUserOptional = roleUserDao.getById(id);
		final RoleDataDto roleDataDto = new RoleDataDto();
		if (roleUserOptional.isPresent()) {
			roleDataDto.setId(roleUserOptional.get().getId());
			roleDataDto.setRoleUserCode(roleUserOptional.get().getRoleUserCode());
			roleDataDto.setRoleUserName(roleUserOptional.get().getRoleUserName());
			roleDataDto.setVer(roleUserOptional.get().getVer());
		}

		RoleDto roleDto = new RoleDto();
		roleDto.setData(roleDataDto);
		return roleDto;
	}

	@Override
	public RoleDto getByCode(String code) {
		Optional<RoleUser> roleUserOptional = Optional.ofNullable(null);

		roleUserOptional = roleUserDao.getByCode(code);

		final RoleDataDto roleDataDto = new RoleDataDto();
		if (roleUserOptional.isPresent()) {
			roleDataDto.setId(roleUserOptional.get().getId());
			roleDataDto.setRoleUserCode(roleUserOptional.get().getRoleUserCode());
			roleDataDto.setRoleUserName(roleUserOptional.get().getRoleUserName());
			roleDataDto.setVer(roleUserOptional.get().getVer());
		}
		RoleDto roleDto = new RoleDto();
		roleDto.setData(roleDataDto);

		return roleDto;
	}

}
