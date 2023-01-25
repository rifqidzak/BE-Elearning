package com.lawencon.e.learning.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.e.learning.dto.role.RoleDto;
import com.lawencon.e.learning.dto.role.RolesDto;
import com.lawencon.e.learning.service.RoleUserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("role")
public class RolesController {
	private final RoleUserService roleUserService;

	public RolesController(RoleUserService roleUserService) {
		this.roleUserService = roleUserService;
	}
	
	@GetMapping("id")
	public ResponseEntity<RoleDto> getById(@RequestParam("id") Long id){
		final RoleDto roleDto = roleUserService.getById(id);
		return new ResponseEntity<>(roleDto, HttpStatus.OK);
	}
	
	@GetMapping("code")
	public ResponseEntity<RoleDto> getById(@RequestParam("code") String code){
		final RoleDto roleDto = roleUserService.getByCode(code);
		return new ResponseEntity<>(roleDto, HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<RolesDto> getAll(){
		final RolesDto roleDto = roleUserService.getAll();
		return new ResponseEntity<>(roleDto, HttpStatus.OK);
	}
}
