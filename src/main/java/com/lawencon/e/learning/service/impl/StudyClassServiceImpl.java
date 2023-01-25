package com.lawencon.e.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.e.learning.constant.MessageEnum;
import com.lawencon.e.learning.dao.FileDao;
import com.lawencon.e.learning.dao.StudyClassDao;
import com.lawencon.e.learning.dao.UserDao;
import com.lawencon.e.learning.dto.studyclass.StudyClassDataDto;
import com.lawencon.e.learning.dto.studyclass.StudyClassDeleteResDto;
import com.lawencon.e.learning.dto.studyclass.StudyClassDto;
import com.lawencon.e.learning.dto.studyclass.StudyClassInsertReqDataDto;
import com.lawencon.e.learning.dto.studyclass.StudyClassInsertResDataDto;
import com.lawencon.e.learning.dto.studyclass.StudyClassInsertResDto;
import com.lawencon.e.learning.dto.studyclass.StudyClassUpdateReqDataDto;
import com.lawencon.e.learning.dto.studyclass.StudyClassUpdateResDataDto;
import com.lawencon.e.learning.dto.studyclass.StudyClassUpdateResDto;
import com.lawencon.e.learning.dto.studyclass.StudyClassesDto;
import com.lawencon.e.learning.model.File;
import com.lawencon.e.learning.model.StudyClass;
import com.lawencon.e.learning.model.User;
import com.lawencon.e.learning.service.PrincipalService;
import com.lawencon.e.learning.service.StudyClassService;

@Service
public class StudyClassServiceImpl implements StudyClassService {

	private final StudyClassDao studyClassDao;
	private final UserDao userDao;
	private final FileDao fileDao;
	private final PrincipalService principalService;
	public StudyClassServiceImpl(StudyClassDao studyClassDao, UserDao userDao, FileDao fileDao, PrincipalService principalService) {
		this.studyClassDao = studyClassDao;
		this.userDao = userDao;
		this.fileDao = fileDao;
		this.principalService = principalService;
	}

	@Transactional
	@Override
	public StudyClassInsertResDto insert(StudyClassInsertReqDataDto data) {
		StudyClass studyClassInsert = new StudyClass();
		final Optional<User> userOptional = userDao.getById(data.getTeacherId());

		studyClassInsert.setTeacherId(userOptional.get());
		studyClassInsert.setStudyClassCode(data.getStudyClassCode());
		studyClassInsert.setStudyClassName(data.getStudyClassName());
		studyClassInsert.setStudyClassDescription(data.getStudyClassDescription());
		studyClassInsert.setCreatedBy(principalService.getPrincipal().getId());
		
		File file = new File();
		file.setFileName(data.getPhotoStudyClass().getFile());
		file.setExtension(data.getPhotoStudyClass().getExtension());
		file.setCreatedBy(principalService.getPrincipal().getId());
		file = fileDao.insert(file);
		studyClassInsert.setPhotoClass(file);

		studyClassInsert = studyClassDao.insert(studyClassInsert);

		final StudyClassInsertResDataDto studyClassInsertResDataDto = new StudyClassInsertResDataDto();
		studyClassInsertResDataDto.setId(studyClassInsert.getId());

		final StudyClassInsertResDto studyClassInsertResDto = new StudyClassInsertResDto();
		studyClassInsertResDto.setData(studyClassInsertResDataDto);
		studyClassInsertResDto.setMessage(MessageEnum.INSERTED.toString());
		return studyClassInsertResDto;
	}

	@Override
	public StudyClassesDto getAllWithTeacher() {
		final List<StudyClass> studyClasses = studyClassDao.getAllWithTeacher();
		final List<StudyClassDataDto> studyClassDataDtos = new ArrayList<>();
		for (int i = 0; i < studyClasses.size(); i++) {
			StudyClassDataDto studyClassDataDto = new StudyClassDataDto();
			studyClassDataDto.setId(studyClasses.get(i).getId());
			studyClassDataDto.setPhotoStudyClass(studyClasses.get(i).getPhotoClass().getId());
			studyClassDataDto.setStudyClassCode(studyClasses.get(i).getStudyClassCode());
			studyClassDataDto.setStudyClassName(studyClasses.get(i).getStudyClassName());
			studyClassDataDto.setTeacherName(studyClasses.get(i).getTeacherId().getFullNameUser());
			studyClassDataDto.setVer(studyClasses.get(i).getVer());
			studyClassDataDto.setIsActive(studyClasses.get(i).getIsActive());
			studyClassDataDto.setDescription(studyClasses.get(i).getStudyClassDescription());
			studyClassDataDtos.add(studyClassDataDto);
		}
		
		StudyClassesDto studyClassesDto = new StudyClassesDto();
		studyClassesDto.setData(studyClassDataDtos);
		return studyClassesDto;
	}

	@Override
	public StudyClassDto getById(Long id) {
		final Optional<StudyClass> studyClassOptional = studyClassDao.getById(id);
		final StudyClassDataDto studyClassDataDto = new StudyClassDataDto();
		if (studyClassOptional.isPresent()) {
			studyClassDataDto.setId(studyClassOptional.get().getId());
			studyClassDataDto.setPhotoStudyClass(studyClassOptional.get().getPhotoClass().getId());
			studyClassDataDto.setStudyClassCode(studyClassOptional.get().getStudyClassCode());
			studyClassDataDto.setStudyClassName(studyClassOptional.get().getStudyClassName());
			studyClassDataDto.setTeacherName(studyClassOptional.get().getTeacherId().getFullNameUser());
			studyClassDataDto.setVer(studyClassOptional.get().getVer());
			studyClassDataDto.setIsActive(studyClassOptional.get().getIsActive());
		}

		StudyClassDto studyClassDto = new StudyClassDto();
		studyClassDto.setData(studyClassDataDto);

		return studyClassDto;
	}

	@Transactional
	@Override
	public StudyClassUpdateResDto update(StudyClassUpdateReqDataDto data) {
		final Optional<StudyClass> studyClassOptional = studyClassDao.getById(data.getId());
		final Optional<User> userOptional = userDao.getById(data.getTeacherId());
		StudyClass studyClassUpdate = new StudyClass();
		if (studyClassOptional.isPresent()) {
			studyClassUpdate = studyClassOptional.get();
			studyClassUpdate.setStudyClassName(data.getStudyClassName());
			studyClassUpdate.setTeacherId(userOptional.get());
			studyClassUpdate.setStudyClassDescription(data.getStudyClassDescription());
			studyClassUpdate.setIsActive(data.getIsActive());
		}

		if (data.getPhotoStudyClass() != null
				&& !data.getPhotoStudyClass().getFile().equals(userOptional.get().getPhotoUser().getFileName())
				&& !data.getPhotoStudyClass().getExtension().equals(userOptional.get().getPhotoUser().getExtension())) {
			File file = new File();
			file.setFileName(data.getPhotoStudyClass().getFile());
			file.setExtension(data.getPhotoStudyClass().getExtension());
			file = fileDao.insert(file);
			studyClassUpdate.setPhotoClass(file);
		}
		studyClassUpdate = studyClassDao.update(studyClassUpdate);

		StudyClassUpdateResDataDto studyClassUpdateResDataDto = new StudyClassUpdateResDataDto();
		studyClassUpdateResDataDto.setVer(studyClassUpdate.getVer());

		StudyClassUpdateResDto studyClassUpdateResDto = new StudyClassUpdateResDto();
		studyClassUpdateResDto.setData(studyClassUpdateResDataDto);
		studyClassUpdateResDto.setMessage(MessageEnum.UPDATED.toString());
		return studyClassUpdateResDto;
	}

	@Override
	public StudyClassDeleteResDto deleteById(Long id) {
		Boolean studyClassDelete = false;
		studyClassDelete = studyClassDao.deleteById(id);
		final StudyClassDeleteResDto studyClassDeleteResDto = new StudyClassDeleteResDto();
		if (studyClassDelete) {
			studyClassDeleteResDto.setMessage(MessageEnum.DELETED.toString());
		}
		return studyClassDeleteResDto;
	}

	@Override
	public StudyClassesDto getAllbyTeachersSide(Long id) {
		final List<StudyClass> studyClasses = studyClassDao.getAllbyTeachersSide(id);
		final List<StudyClassDataDto> studyClassDataDtos = new ArrayList<>();
		for (int i = 0; i < studyClasses.size(); i++) {
			StudyClassDataDto studyClassDataDto = new StudyClassDataDto();
			studyClassDataDto.setId(studyClasses.get(i).getId());
			studyClassDataDto.setPhotoStudyClass(studyClasses.get(i).getPhotoClass().getId());
			studyClassDataDto.setStudyClassCode(studyClasses.get(i).getStudyClassCode());
			studyClassDataDto.setStudyClassName(studyClasses.get(i).getStudyClassName());
			studyClassDataDto.setTeacherName(studyClasses.get(i).getTeacherId().getFullNameUser());
			studyClassDataDto.setVer(studyClasses.get(i).getVer());
			studyClassDataDto.setIsActive(studyClasses.get(i).getIsActive());
			studyClassDataDto.setDescription(studyClasses.get(i).getStudyClassDescription());
			studyClassDataDtos.add(studyClassDataDto);
		}
		
		StudyClassesDto studyClassesDto = new StudyClassesDto();
		studyClassesDto.setData(studyClassDataDtos);
		return studyClassesDto;
	}

}
