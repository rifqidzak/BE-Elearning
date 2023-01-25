package com.lawencon.e.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.e.learning.constant.MessageEnum;
import com.lawencon.e.learning.dao.CollectionQuizDao;
import com.lawencon.e.learning.dao.FileDao;
import com.lawencon.e.learning.dao.ScheduleQuizDao;
import com.lawencon.e.learning.dao.UserDao;
import com.lawencon.e.learning.dto.collectionquiz.CollectionQuizDataDto;
import com.lawencon.e.learning.dto.collectionquiz.CollectionQuizDto;
import com.lawencon.e.learning.dto.collectionquiz.CollectionQuizInsertReqDataDto;
import com.lawencon.e.learning.dto.collectionquiz.CollectionQuizInsertResDataDto;
import com.lawencon.e.learning.dto.collectionquiz.CollectionQuizInsertResDto;
import com.lawencon.e.learning.dto.collectionquiz.CollectionQuizUpdateReqDataDto;
import com.lawencon.e.learning.dto.collectionquiz.CollectionQuizUpdateResDataDto;
import com.lawencon.e.learning.dto.collectionquiz.CollectionQuizUpdateResDto;
import com.lawencon.e.learning.dto.collectionquiz.CollectionQuizsDto;
import com.lawencon.e.learning.model.CollectionQuiz;
import com.lawencon.e.learning.model.File;
import com.lawencon.e.learning.model.ScheduleQuiz;
import com.lawencon.e.learning.model.User;
import com.lawencon.e.learning.service.CollectionQuizService;
import com.lawencon.e.learning.service.PrincipalService;

@Service
public class CollectionQuizServiceImpl implements CollectionQuizService {

	private final CollectionQuizDao collectionQuizDao;
	private final FileDao fileDao;
	private final UserDao userDao;
	private final ScheduleQuizDao scheduleQuizDao;
	private final PrincipalService principalService;

	CollectionQuizServiceImpl(CollectionQuizDao collectionQuizDao, FileDao fileDao, UserDao userDao,
			ScheduleQuizDao scheduleQuizDao, PrincipalService principalService) {
		this.collectionQuizDao = collectionQuizDao;
		this.fileDao = fileDao;
		this.userDao = userDao;
		this.scheduleQuizDao = scheduleQuizDao;
		this.principalService = principalService;
	}

	@Transactional
	@Override
	public CollectionQuizInsertResDto insert(CollectionQuizInsertReqDataDto data) {
		CollectionQuiz collectionQuizInsert = new CollectionQuiz();
		Optional<ScheduleQuiz> scheduleQuizOptional = Optional.ofNullable(null);
		scheduleQuizOptional = scheduleQuizDao.getById(data.getScheduleId());
		final Optional<User> userOptional = userDao.getById(principalService.getPrincipal().getId());

		File fileInsert = new File();
		fileInsert.setFileName(data.getFile().getFile());
		fileInsert.setExtension(data.getFile().getExtension());
		fileInsert.setCreatedBy(principalService.getPrincipal().getId());
		fileInsert = fileDao.insert(fileInsert);
		collectionQuizInsert.setFileId(fileInsert);
		collectionQuizInsert.setScheduleId(scheduleQuizOptional.get());
		collectionQuizInsert.setUserId(userOptional.get());
		collectionQuizInsert.setCreatedBy(principalService.getPrincipal().getId());
		collectionQuizInsert = collectionQuizDao.insert(collectionQuizInsert);

		final CollectionQuizInsertResDataDto collectionQuizInsertResDataDto = new CollectionQuizInsertResDataDto();
		collectionQuizInsertResDataDto.setId(collectionQuizInsert.getId());

		final CollectionQuizInsertResDto collectionQuizInsertResDto = new CollectionQuizInsertResDto();
		collectionQuizInsertResDto.setData(collectionQuizInsertResDataDto);
		collectionQuizInsertResDto.setMessage(MessageEnum.INSERTED.toString());
		return collectionQuizInsertResDto;
	}

	@Override
	public CollectionQuizDto getById(Long id) {
		final Optional<CollectionQuiz> collectionQuizOptional = collectionQuizDao.getById(id);
		final CollectionQuizDataDto collectionQuizDataDto = new CollectionQuizDataDto();

		if (collectionQuizOptional.isPresent()) {
			collectionQuizDataDto.setQuizName(collectionQuizOptional.get().getScheduleId().getQuizId().getQuizName());
			collectionQuizDataDto.setNote(collectionQuizOptional.get().getNote());
			collectionQuizDataDto.setScore(collectionQuizOptional.get().getScore());
			collectionQuizDataDto.setStudentName(collectionQuizOptional.get().getUserId().getFullNameUser());
			collectionQuizDataDto.setQuizStart(collectionQuizOptional.get().getScheduleId().getStartAt());
			collectionQuizDataDto.setQuizFinish(collectionQuizOptional.get().getScheduleId().getFinishOn());
			collectionQuizDataDto.setId(collectionQuizOptional.get().getId());
			collectionQuizDataDto.setVer(collectionQuizOptional.get().getVer());
			collectionQuizDataDto.setFileId(collectionQuizOptional.get().getFileId().getId());
		}
		final CollectionQuizDto collectionQuizDto = new CollectionQuizDto();
		collectionQuizDto.setData(collectionQuizDataDto);

		return collectionQuizDto;
	}

	@Override
	public CollectionQuizsDto getAllByTeacherSide(Long studyClassId) {
		final List<CollectionQuiz> collectionQuizs = collectionQuizDao.getAllByTeacherSide(studyClassId);
		final List<CollectionQuizDataDto> collectionQuizDataDtos = new ArrayList<>();

		for (int i = 0; i < collectionQuizs.size(); i++) {
			final CollectionQuizDataDto collectionQuizDataDto = new CollectionQuizDataDto();
			collectionQuizDataDto.setQuizName(collectionQuizs.get(i).getScheduleId().getQuizId().getQuizName());
			collectionQuizDataDto.setNote(collectionQuizs.get(i).getNote());
			collectionQuizDataDto.setScore(collectionQuizs.get(i).getScore());
			collectionQuizDataDto.setStudentName(collectionQuizs.get(i).getUserId().getFullNameUser());
			collectionQuizDataDto.setQuizStart(collectionQuizs.get(i).getScheduleId().getStartAt());
			collectionQuizDataDto.setQuizFinish(collectionQuizs.get(i).getScheduleId().getFinishOn());
			collectionQuizDataDto.setId(collectionQuizs.get(i).getId());
			collectionQuizDataDto.setVer(collectionQuizs.get(i).getVer());
			collectionQuizDataDto.setFileId(collectionQuizs.get(i).getFileId().getId());
			collectionQuizDataDtos.add(collectionQuizDataDto);
		}

		CollectionQuizsDto collectionQuizsDto = new CollectionQuizsDto();
		collectionQuizsDto.setData(collectionQuizDataDtos);
		return collectionQuizsDto;
	}

	@Transactional
	@Override
	public CollectionQuizUpdateResDto scoring(CollectionQuizUpdateReqDataDto data) {
		final Optional<CollectionQuiz> collectionQuizOptional = collectionQuizDao.getById(data.getId());
		CollectionQuiz collectionQuizUpdate = new CollectionQuiz();
		if (collectionQuizOptional.isPresent()) {
			collectionQuizUpdate = collectionQuizOptional.get();
			collectionQuizUpdate.setScore(data.getScore());
			collectionQuizUpdate.setNote(data.getNote());
			collectionQuizUpdate.setUpdatedBy(principalService.getPrincipal().getId());
		}
		collectionQuizUpdate = collectionQuizDao.scoring(collectionQuizUpdate);

		final CollectionQuizUpdateResDataDto collectionQuizUpdateResDataDto = new CollectionQuizUpdateResDataDto();
		collectionQuizUpdateResDataDto.setVer(collectionQuizUpdate.getVer());

		final CollectionQuizUpdateResDto collectionQuizUpdateResDto = new CollectionQuizUpdateResDto();
		collectionQuizUpdateResDto.setData(collectionQuizUpdateResDataDto);
		collectionQuizUpdateResDto.setMessage(MessageEnum.UPDATED.toString());
		return collectionQuizUpdateResDto;
	}

}
