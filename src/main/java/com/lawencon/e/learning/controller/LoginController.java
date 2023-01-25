package com.lawencon.e.learning.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.e.learning.dto.login.LoginReqDto;
import com.lawencon.e.learning.dto.login.LoginResDto;
import com.lawencon.e.learning.model.User;
import com.lawencon.e.learning.service.JwtService;
import com.lawencon.e.learning.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("login")
public class LoginController {
	private final AuthenticationManager authenticationManager;
	private final UserService userService;
	private final JwtService jwtService;

	public LoginController(AuthenticationManager authenticationManager, UserService userService,
			JwtService jwtService) {
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.jwtService = jwtService;
	}

	@PostMapping
	public ResponseEntity<LoginResDto> Login(@RequestBody LoginReqDto loginReqDto) {
		final Authentication object = new UsernamePasswordAuthenticationToken(loginReqDto.getEmail(),
				loginReqDto.getPassword());
		authenticationManager.authenticate(object);
		final Optional<User> userOptional = userService.getByEmail(loginReqDto.getEmail());
		final Map<String, Object> claims = new HashMap<>();

		final Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, 1);
		claims.put("exp", cal.getTime());
		claims.put("id", userOptional.get().getId());
		claims.put("rc", userOptional.get().getRoleUserId().getRoleUserCode());

		final LoginResDto loginResDto = new LoginResDto();
		loginResDto.setId(userOptional.get().getId());
		loginResDto.setFullName(userOptional.get().getFullNameUser());
		loginResDto.setRoleCode(userOptional.get().getRoleUserId().getRoleUserCode());
		if(userOptional.get().getPhotoUser()!=null) {
			loginResDto.setPhotoId(userOptional.get().getPhotoUser().getId());
		}
		loginResDto.setToken(jwtService.generateJwt(claims));
		loginResDto.setVer(userOptional.get().getVer());

		return new ResponseEntity<LoginResDto>(loginResDto, HttpStatus.OK);
	}
}
