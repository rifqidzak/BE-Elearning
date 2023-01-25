package com.lawencon.e.learning.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.e.learning.constant.MessageEnum;
import com.lawencon.e.learning.dao.FileDao;
import com.lawencon.e.learning.dao.RoleUserDao;
import com.lawencon.e.learning.dao.UserDao;
import com.lawencon.e.learning.dto.user.UserDataDto;
import com.lawencon.e.learning.dto.user.UserDeleteResDto;
import com.lawencon.e.learning.dto.user.UserDto;
import com.lawencon.e.learning.dto.user.UserInsertReqDataDto;
import com.lawencon.e.learning.dto.user.UserInsertResDataDto;
import com.lawencon.e.learning.dto.user.UserInsertResDto;
import com.lawencon.e.learning.dto.user.UserUpdateReqDataDto;
import com.lawencon.e.learning.dto.user.UserUpdateResDataDto;
import com.lawencon.e.learning.dto.user.UserUpdateResDto;
import com.lawencon.e.learning.dto.user.UsersDto;
import com.lawencon.e.learning.model.File;
import com.lawencon.e.learning.model.RoleUser;
import com.lawencon.e.learning.model.User;
import com.lawencon.e.learning.pojo.SendEmailPojo;
import com.lawencon.e.learning.service.GenerateService;
import com.lawencon.e.learning.service.JavaMailService;
import com.lawencon.e.learning.service.PrincipalService;
import com.lawencon.e.learning.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private final UserDao userDao;
	private final RoleUserDao roleUserDao;
	private final FileDao fileDao;
	private final GenerateService generateService;
	private final PasswordEncoder passwordEncoder;
	private final JavaMailService javaMailService;
	private final PrincipalService principalService;
	public UserServiceImpl(UserDao userDao, RoleUserDao roleUserDao, FileDao fileDao, GenerateService generateService,
			PasswordEncoder passwordEncoder, JavaMailService javaMailService, PrincipalService principalService) {
		this.userDao = userDao;
		this.roleUserDao = roleUserDao;
		this.fileDao = fileDao;
		this.generateService = generateService;
		this.passwordEncoder = passwordEncoder;
		this.javaMailService = javaMailService;
		this.principalService = principalService;
	}

	@Transactional
	@Override
	public UserInsertResDto insert(UserInsertReqDataDto data) {
		File fileInsert = new File();
		if(data.getFileInsertReqDto().getFile()!= null && data.getFileInsertReqDto().getExtension()!= null) {			
			fileInsert.setFileName(data.getFileInsertReqDto().getFile());
			fileInsert.setExtension(data.getFileInsertReqDto().getExtension());
//			fileInsert.setCreatedBy(principalService.getPrincipal().getId());
			fileInsert.setCreatedBy(1l);
			fileInsert = fileDao.insert(fileInsert);
		}

		User userInsert = new User();
		final Optional<RoleUser> roleUserOptional = roleUserDao.getById(data.getRoleId());
		userInsert.setRoleUserId(roleUserOptional.get());
		if(fileInsert !=null) {			
			userInsert.setPhotoUser(fileInsert);
		}
		userInsert.setFullNameUser(data.getFullNameUser());
		userInsert.setEmail(data.getEmail());
		userInsert.setPassword(generateService.generate(5));
//		userInsert.setCreatedBy(principalService.getPrincipal().getId());
		userInsert.setCreatedBy(1l);
		final String passwordEmail = userInsert.getPassword();
		final String hashPassword = passwordEncoder.encode(userInsert.getPassword());

		userInsert.setPassword(hashPassword);
		userInsert = userDao.insert(userInsert);

		final SendEmailPojo createdAccountPojo = new SendEmailPojo();
		createdAccountPojo.setEmail(userInsert.getEmail());
		createdAccountPojo.setSubject("You Can Check Your E-Learning Account !");
		createdAccountPojo.setBody(
				"Your Account : \n" + " " + " Email : " + userInsert.getEmail() + "\n" + "Password : " + passwordEmail);

		final Runnable r = () -> javaMailService.sendEmail(createdAccountPojo);
		final Thread thread = new Thread(r);
		thread.start();
		
		UserInsertResDataDto userInsertResDataDto = new UserInsertResDataDto();
		userInsertResDataDto.setId(userInsert.getId());

		UserInsertResDto userInsertResDto = new UserInsertResDto();
		userInsertResDto.setData(userInsertResDataDto);
		userInsertResDto.setMessage(MessageEnum.INSERTED.toString());

		return userInsertResDto;
	}

	@Transactional
	@Override
	public UserUpdateResDto update(UserUpdateReqDataDto data) {
		final Optional<User> userOptional = userDao.getById(data.getId());
		User userUpdate = new User();
		if (userOptional.isPresent()) {
			userUpdate = userOptional.get();
			if (data.getFileInsertReqDto() != null
					&& !userUpdate.getPhotoUser().getFileName().equals(data.getFullNameUser())
					&& !userUpdate.getPhotoUser().getExtension().equals(data.getFileInsertReqDto().getExtension())) {

				final File file = new File();
				file.setFileName(data.getFileInsertReqDto().getFile());
				file.setExtension(data.getFileInsertReqDto().getExtension());
				fileDao.insert(file);
				userUpdate.setPhotoUser(file);
			}
			userUpdate.setFullNameUser(data.getFullNameUser());
			userUpdate.setIsActive(data.getIsActive());
			userUpdate.setUpdatedBy(principalService.getPrincipal().getId());
			final Optional<RoleUser> roleUserOptional = roleUserDao.getById(data.getRoleId());
			userUpdate.setRoleUserId(roleUserOptional.get());
		}
		userUpdate = userDao.update(userUpdate);

		UserUpdateResDataDto userUpdateResDataDto = new UserUpdateResDataDto();
		userUpdateResDataDto.setVer(userUpdate.getVer());

		UserUpdateResDto userUpdateResDto = new UserUpdateResDto();
		userUpdateResDto.setData(userUpdateResDataDto);
		userUpdateResDto.setMessage(MessageEnum.UPDATED.toString());
		return userUpdateResDto;

	}

	@Override
	public UserDto getById(Long id) {
		final Optional<User> userOptional = userDao.getById(id);
		UserDataDto userDataDto = new UserDataDto();
		userDataDto.setFullNameUser(userOptional.get().getFullNameUser());
		userDataDto.setId(userOptional.get().getId());
		if(userOptional.get().getPhotoUser()!= null) {			
			userDataDto.setPhotoId(userOptional.get().getPhotoUser().getId());
		}
		userDataDto.setRoleUser(userOptional.get().getRoleUserId().getRoleUserCode());
		userDataDto.setVer(userOptional.get().getVer());
		userDataDto.setIsActive(userOptional.get().getIsActive());
		UserDto userDto = new UserDto();
		userDto.setData(userDataDto);
		return userDto;
	}

	@Override
	public UsersDto getAll() {
		List<User> users = new ArrayList<>();
		users = userDao.getAll();
		List<UserDataDto> userDataDtos = new ArrayList<>();
		for (int i = 0; i < users.size(); i++) {
			UserDataDto userDataDto = new UserDataDto();
			userDataDto.setFullNameUser(users.get(i).getFullNameUser());
			userDataDto.setId(users.get(i).getId());
			if(users.get(i).getPhotoUser() != null) {
				userDataDto.setPhotoId(users.get(i).getPhotoUser().getId());				
			}
			userDataDto.setRoleUser(users.get(i).getRoleUserId().getRoleUserCode());
			userDataDto.setVer(users.get(i).getVer());
			userDataDto.setIsActive(users.get(i).getIsActive());
			userDataDtos.add(userDataDto);
		}
		UsersDto usersDto = new UsersDto();
		usersDto.setData(userDataDtos);
		return usersDto;
	}

	@Transactional
	@Override
	public UserDeleteResDto deleteById(Long id) {
		Boolean userDelete = false;
		userDelete = userDao.deleteById(id);
		final UserDeleteResDto userDeleteResDto = new UserDeleteResDto();
		if (userDelete) {
			userDeleteResDto.setMessage(MessageEnum.DELETED.toString());
		}
		return userDeleteResDto;
	}

	@Override
	public Optional<User> getByEmail(String email) {
		return userDao.getByEmail(email);
	}

	@Override
	public UsersDto getAllByRole(String roleCode) {
		List<User> users = new ArrayList<>();
		final UsersDto usersDto = new UsersDto();
		try {
			users = userDao.getAllByRole(roleCode);
			List<UserDataDto> userDataDtos = new ArrayList<>();
			for (int i = 0; i < users.size(); i++) {
				final UserDataDto userDataDto = new UserDataDto();
				userDataDto.setFullNameUser(users.get(i).getFullNameUser());
				userDataDto.setId(users.get(i).getId());
				userDataDto.setPhotoId(users.get(i).getPhotoUser().getId());
				userDataDto.setRoleUser(users.get(i).getRoleUserId().getRoleUserCode());
				userDataDto.setVer(users.get(i).getVer());
				userDataDto.setIsActive(users.get(i).getIsActive());
				userDataDtos.add(userDataDto);
			}
			usersDto.setData(userDataDtos);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersDto;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final Optional<User> usersOptional = userDao.getByEmail(username);

		if (usersOptional.isPresent()) {
			return new org.springframework.security.core.userdetails.User(username, usersOptional.get().getPassword(),
					new ArrayList<>());
		}

		throw new UsernameNotFoundException("Email And Password Wrong");
	}

}
