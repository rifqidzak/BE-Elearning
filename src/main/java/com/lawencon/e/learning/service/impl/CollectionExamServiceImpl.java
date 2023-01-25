package com.lawencon.e.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.e.learning.constant.MessageEnum;
import com.lawencon.e.learning.dao.CollectionExamDao;
import com.lawencon.e.learning.dao.FileDao;
import com.lawencon.e.learning.dao.ScheduleExamDao;
import com.lawencon.e.learning.dao.UserDao;
import com.lawencon.e.learning.dto.collectionexam.CollectionExamDataDto;
import com.lawencon.e.learning.dto.collectionexam.CollectionExamDto;
import com.lawencon.e.learning.dto.collectionexam.CollectionExamInsertReqDataDto;
import com.lawencon.e.learning.dto.collectionexam.CollectionExamInsertResDataDto;
import com.lawencon.e.learning.dto.collectionexam.CollectionExamInsertResDto;
import com.lawencon.e.learning.dto.collectionexam.CollectionExamUpdateReqDataDto;
import com.lawencon.e.learning.dto.collectionexam.CollectionExamUpdateResDataDto;
import com.lawencon.e.learning.dto.collectionexam.CollectionExamUpdateResDto;
import com.lawencon.e.learning.dto.collectionexam.CollectionExamsDto;
import com.lawencon.e.learning.model.CollectionExam;
import com.lawencon.e.learning.model.File;
import com.lawencon.e.learning.model.ScheduleExam;
import com.lawencon.e.learning.model.User;
import com.lawencon.e.learning.service.CollectionExamService;
import com.lawencon.e.learning.service.PrincipalService;

@Service
public class CollectionExamServiceImpl implements CollectionExamService {
	private final CollectionExamDao collectionExamDao;
	private final FileDao fileDao;
	private final UserDao userDao;
	private final ScheduleExamDao scheduleExamDao;
	private final PrincipalService principalService;

	CollectionExamServiceImpl(CollectionExamDao collectionExamDao, FileDao fileDao, UserDao userDao,
			ScheduleExamDao scheduleExamDao, PrincipalService principalService) {
		this.collectionExamDao = collectionExamDao;
		this.fileDao = fileDao;
		this.userDao = userDao;
		this.scheduleExamDao = scheduleExamDao;
		this.principalService = principalService;
	}

	@Transactional
	@Override
	public CollectionExamInsertResDto insert(CollectionExamInsertReqDataDto data) {
		CollectionExam collectionExamInsert = new CollectionExam();
		Optional<ScheduleExam> scheduleExamOptional = Optional.ofNullable(null);
		scheduleExamOptional = scheduleExamDao.getById(data.getScheduleId());
		final Optional<User> userOptional = userDao.getById(principalService.getPrincipal().getId());

		File fileInsert = new File();
		fileInsert.setFileName(data.getFile().getFile());
		fileInsert.setExtension(data.getFile().getExtension());
		fileInsert.setCreatedBy(principalService.getPrincipal().getId());
		fileInsert = fileDao.insert(fileInsert);

		collectionExamInsert.setFileId(fileInsert);
		collectionExamInsert.setScheduleId(scheduleExamOptional.get());
		collectionExamInsert.setUserId(userOptional.get());
		collectionExamInsert.setCreatedBy(principalService.getPrincipal().getId());
		collectionExamInsert = collectionExamDao.insert(collectionExamInsert);

		final CollectionExamInsertResDataDto collectionExamInsertResDataDto = new CollectionExamInsertResDataDto();
		collectionExamInsertResDataDto.setId(collectionExamInsert.getId());

		final CollectionExamInsertResDto collectionExamInsertResDto = new CollectionExamInsertResDto();
		collectionExamInsertResDto.setData(collectionExamInsertResDataDto);
		collectionExamInsertResDto.setMessage(MessageEnum.INSERTED.toString());
		return collectionExamInsertResDto;
	}

	@Override
	public CollectionExamDto getById(Long id) {
		final Optional<CollectionExam> collectionExamOptional = collectionExamDao.getById(id);
		final CollectionExamDataDto collectionExamDataDto = new CollectionExamDataDto();

		if (collectionExamOptional.isPresent()) {
			collectionExamDataDto.setExamName(collectionExamOptional.get().getScheduleId().getExamId().getExamName());
			collectionExamDataDto.setNote(collectionExamOptional.get().getNote());
			collectionExamDataDto.setScore(collectionExamOptional.get().getScore());
			collectionExamDataDto.setStudentName(collectionExamOptional.get().getUserId().getFullNameUser());
			collectionExamDataDto.setExamStart(collectionExamOptional.get().getScheduleId().getStartAt());
			collectionExamDataDto.setExamFinish(collectionExamOptional.get().getScheduleId().getFinishOn());
			collectionExamDataDto.setId(collectionExamOptional.get().getId());
			collectionExamDataDto.setVer(collectionExamOptional.get().getVer());
			collectionExamDataDto.setFileId(collectionExamOptional.get().getFileId().getId());
		}
		final CollectionExamDto collectionExamDto = new CollectionExamDto();
		collectionExamDto.setData(collectionExamDataDto);

		return collectionExamDto;
	}

	@Override
	public CollectionExamsDto getAllByTeacherSide(Long studyClassId) {
		final List<CollectionExam> collectionExams = collectionExamDao.getAllByTeacherSide(studyClassId);
		final List<CollectionExamDataDto> collectionExamDataDtos = new ArrayList<>();

		for (int i = 0; i < collectionExams.size(); i++) {
			final CollectionExamDataDto collectionExamDataDto = new CollectionExamDataDto();
			collectionExamDataDto.setExamName(collectionExams.get(i).getScheduleId().getExamId().getExamName());
			collectionExamDataDto.setNote(collectionExams.get(i).getNote());
			collectionExamDataDto.setScore(collectionExams.get(i).getScore());
			collectionExamDataDto.setStudentName(collectionExams.get(i).getUserId().getFullNameUser());
			collectionExamDataDto.setExamStart(collectionExams.get(i).getScheduleId().getStartAt());
			collectionExamDataDto.setExamFinish(collectionExams.get(i).getScheduleId().getFinishOn());
			collectionExamDataDto.setId(collectionExams.get(i).getId());
			collectionExamDataDto.setVer(collectionExams.get(i).getVer());
			collectionExamDataDto.setFileId(collectionExams.get(i).getFileId().getId());
			collectionExamDataDtos.add(collectionExamDataDto);
		}

		CollectionExamsDto collectionExamsDto = new CollectionExamsDto();
		collectionExamsDto.setData(collectionExamDataDtos);
		return collectionExamsDto;
	}

	@Transactional
	@Override
	public CollectionExamUpdateResDto scoring(CollectionExamUpdateReqDataDto data) {
		final Optional<CollectionExam> collectionExamOptional = collectionExamDao.getById(data.getId());
		CollectionExam collectionExamUpdate = new CollectionExam();
		if (collectionExamOptional.isPresent()) {
			collectionExamUpdate = collectionExamOptional.get();
			collectionExamUpdate.setScore(data.getScore());
			collectionExamUpdate.setNote(data.getNote());
			collectionExamUpdate.setUpdatedBy(principalService.getPrincipal().getId());
		}
		collectionExamUpdate = collectionExamDao.scoring(collectionExamUpdate);

		final CollectionExamUpdateResDataDto collectionExamUpdateResDataDto = new CollectionExamUpdateResDataDto();
		collectionExamUpdateResDataDto.setVer(collectionExamUpdate.getVer());

		final CollectionExamUpdateResDto collectionExamUpdateResDto = new CollectionExamUpdateResDto();
		collectionExamUpdateResDto.setData(collectionExamUpdateResDataDto);
		collectionExamUpdateResDto.setMessage(MessageEnum.UPDATED.toString());
		return collectionExamUpdateResDto;
	}

}
