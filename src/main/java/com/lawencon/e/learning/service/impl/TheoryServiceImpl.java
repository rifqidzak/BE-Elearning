package com.lawencon.e.learning.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.e.learning.constant.MessageEnum;
import com.lawencon.e.learning.dao.AttachmentTheoryDao;
import com.lawencon.e.learning.dao.FileDao;
import com.lawencon.e.learning.dao.ScheduleTheoryDao;
import com.lawencon.e.learning.dao.StudyClassDao;
import com.lawencon.e.learning.dao.TheoryDao;
import com.lawencon.e.learning.dto.theory.TheoryInsertReqDataDto;
import com.lawencon.e.learning.dto.theory.TheoryInsertResDataDto;
import com.lawencon.e.learning.dto.theory.TheoryInsertResDto;
import com.lawencon.e.learning.model.AttachmentTheory;
import com.lawencon.e.learning.model.File;
import com.lawencon.e.learning.model.ScheduleTheory;
import com.lawencon.e.learning.model.StudyClass;
import com.lawencon.e.learning.model.Theory;
import com.lawencon.e.learning.service.PrincipalService;
import com.lawencon.e.learning.service.TheoryService;

@Service
public class TheoryServiceImpl implements TheoryService {

	private final TheoryDao theoryDao;
	private final FileDao fileDao;
	private final AttachmentTheoryDao attachmentTheoryDao;
	private final ScheduleTheoryDao scheduleTheoryDao;
	private final StudyClassDao studyClassDao;
	private final PrincipalService principalService;
	public TheoryServiceImpl(TheoryDao theoryDao, FileDao fileDao, AttachmentTheoryDao attachmentTheoryDao,
			ScheduleTheoryDao scheduleTheoryDao, StudyClassDao studyClassDao, PrincipalService principalService) {
		this.theoryDao = theoryDao;
		this.fileDao = fileDao;
		this.attachmentTheoryDao = attachmentTheoryDao;
		this.scheduleTheoryDao = scheduleTheoryDao;
		this.studyClassDao = studyClassDao;
		this.principalService = principalService;
	}

	@Transactional
	@Override
	public TheoryInsertResDto insert(TheoryInsertReqDataDto data) {
		Theory theoryInsert = new Theory();

		final Optional<ScheduleTheory> scheduleTheoryOptional = scheduleTheoryDao
				.getByFinishOnAndStartAt(data.getStartAt().atStartOfDay(), data.getFinishOn().atStartOfDay(), data.getStudyClassId());
		if (scheduleTheoryOptional.isPresent()) {
			throw new RuntimeException("Duplicate Schedule!");
		} else {
			theoryInsert.setTheoryCode(data.getTheoryCode());
			theoryInsert.setTheoryName(data.getTheoryName());
			theoryInsert.setCreatedBy(principalService.getPrincipal().getId());
			Optional<StudyClass> studyClassOptional = studyClassDao.getById(data.getStudyClassId());
			theoryInsert.setStudyClassId(studyClassOptional.get());
			theoryInsert = theoryDao.insert(theoryInsert);
			if(data.getFile() != null) {
				for (int i = 0; i < data.getFile().size(); i++) {
					final AttachmentTheory attachmentTheory = new AttachmentTheory();
					File file = new File();
					file.setFileName(data.getFile().get(i).getFile());
					file.setExtension(data.getFile().get(i).getExtension());
					file.setCreatedBy(principalService.getPrincipal().getId());
					file = fileDao.insert(file);
					attachmentTheory.setFileId(file);
					attachmentTheory.setTheoryId(theoryInsert);
					attachmentTheory.setCreatedBy(principalService.getPrincipal().getId());
					attachmentTheoryDao.insert(attachmentTheory);
				}				
			}
			final ScheduleTheory scheduleTheory = new ScheduleTheory();
			scheduleTheory.setStartAt(data.getStartAt().atStartOfDay());
			scheduleTheory.setFinishOn(data.getFinishOn().atStartOfDay());
			scheduleTheory.setCreatedBy(principalService.getPrincipal().getId());
			scheduleTheory.setTheoryId(theoryInsert);
			scheduleTheoryDao.insert(scheduleTheory);

			final TheoryInsertResDataDto theoryInsertResDataDto = new TheoryInsertResDataDto();
			theoryInsertResDataDto.setId(theoryInsert.getId());

			final TheoryInsertResDto theoryInsertResDto = new TheoryInsertResDto();
			theoryInsertResDto.setData(theoryInsertResDataDto);
			theoryInsertResDto.setMessage(MessageEnum.INSERTED.toString());
			return theoryInsertResDto;
		}
	}

	@Override
	public boolean deleteById(Long id) {
		return false;
	}

}
