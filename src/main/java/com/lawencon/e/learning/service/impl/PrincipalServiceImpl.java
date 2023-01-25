package com.lawencon.e.learning.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.lawencon.e.learning.dao.UserDao;
import com.lawencon.e.learning.model.User;
import com.lawencon.e.learning.service.PrincipalService;

@Service
public class PrincipalServiceImpl implements PrincipalService {

	private UserDao userDao;

	@Autowired
	public void setUsersDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User getPrincipal() {
		final Long id = Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		final Optional<User> usersOptional = userDao.getById(id);

		if (usersOptional.isPresent()) {
			return usersOptional.get();
		}
		throw new RuntimeException("Invalid Login");
	}

}
