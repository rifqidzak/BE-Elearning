package com.lawencon.e.learning.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.e.learning.dto.user.UserDeleteResDto;
import com.lawencon.e.learning.dto.user.UserDto;
import com.lawencon.e.learning.dto.user.UserInsertReqDataDto;
import com.lawencon.e.learning.dto.user.UserInsertResDto;
import com.lawencon.e.learning.dto.user.UserUpdateReqDataDto;
import com.lawencon.e.learning.dto.user.UserUpdateResDto;
import com.lawencon.e.learning.dto.user.UsersDto;
import com.lawencon.e.learning.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("users")
public class UsersController {

	private final UserService userService;

	public UsersController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("{id}")
	public ResponseEntity<UserDto> getById(@PathVariable("id") Long id) {
		final UserDto usersById = userService.getById(id);
		return new ResponseEntity<>(usersById, HttpStatus.OK);
	}

	@GetMapping("code")
	public ResponseEntity<UsersDto> getByCode(@RequestParam("code") String code) {
		final UsersDto usersByRoleDto = userService.getAllByRole(code);
		return new ResponseEntity<>(usersByRoleDto, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<UsersDto> getAll() {
		final UsersDto usersDto = userService.getAll();
		return new ResponseEntity<>(usersDto, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UserInsertResDto> insert(@RequestBody @Valid UserInsertReqDataDto data) {
		final UserInsertResDto userInsertResDto = userService.insert(data);
		return new ResponseEntity<>(userInsertResDto, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UserUpdateResDto> update(@RequestBody @Valid UserUpdateReqDataDto data) {
		final UserUpdateResDto userUpdateResDto = userService.update(data);
		return new ResponseEntity<>(userUpdateResDto, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<UserDeleteResDto> delete(@PathVariable("id") Long id) {
		final UserDeleteResDto deleteResDto = userService.deleteById(id);
		return new ResponseEntity<>(deleteResDto, HttpStatus.OK);
	}

}
