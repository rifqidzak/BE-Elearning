
package com.lawencon.e.learning.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.lawencon.e.learning.dto.user.UserDeleteResDto;
import com.lawencon.e.learning.dto.user.UserDto;
import com.lawencon.e.learning.dto.user.UserInsertReqDataDto;
import com.lawencon.e.learning.dto.user.UserInsertResDto;
import com.lawencon.e.learning.dto.user.UserUpdateReqDataDto;
import com.lawencon.e.learning.dto.user.UserUpdateResDto;
import com.lawencon.e.learning.dto.user.UsersDto;
import com.lawencon.e.learning.model.User;

public interface UserService extends UserDetailsService{
	UserInsertResDto insert(final UserInsertReqDataDto data);

	UserUpdateResDto update(final UserUpdateReqDataDto data);

	UserDto getById(final Long id);

	UsersDto getAll();

	UserDeleteResDto deleteById(final Long id);

	Optional<User> getByEmail(final String email);

	UsersDto getAllByRole(final String roleCode);
}
