package com.lawencon.e.learning.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.e.learning.constant.MessageEnum;
import com.lawencon.e.learning.dao.AttachmentExamDao;
import com.lawencon.e.learning.dao.ExamDao;
import com.lawencon.e.learning.dao.FileDao;
import com.lawencon.e.learning.dao.ScheduleExamDao;
import com.lawencon.e.learning.dao.StudyClassDao;
import com.lawencon.e.learning.dto.exam.ExamInsertReqDataDto;
import com.lawencon.e.learning.dto.exam.ExamInsertResDataDto;
import com.lawencon.e.learning.dto.exam.ExamInsertResDto;
import com.lawencon.e.learning.model.AttachmentExam;
import com.lawencon.e.learning.model.Exam;
import com.lawencon.e.learning.model.File;
import com.lawencon.e.learning.model.ScheduleExam;
import com.lawencon.e.learning.model.StudyClass;
import com.lawencon.e.learning.service.ExamService;
import com.lawencon.e.learning.service.PrincipalService;

@Service
public class ExamServiceImpl implements ExamService {

	private final ExamDao examDao;
	private final FileDao fileDao;
	private final AttachmentExamDao attachmentExamDao;
	private final ScheduleExamDao scheduleExamDao;
	private final StudyClassDao studyClassDao;
	private final PrincipalService principalService;
	public ExamServiceImpl(ExamDao examDao, FileDao fileDao, AttachmentExamDao attachmentExamDao,
			ScheduleExamDao scheduleExamDao, StudyClassDao studyClassDao, PrincipalService principalService) {
		this.examDao = examDao;
		this.fileDao = fileDao;
		this.attachmentExamDao = attachmentExamDao;
		this.scheduleExamDao = scheduleExamDao;
		this.studyClassDao = studyClassDao;
		this.principalService = principalService;
	}

	@Transactional
	@Override
	public ExamInsertResDto insert(ExamInsertReqDataDto data) {
		Exam examInsert = new Exam();

		final Optional<ScheduleExam> scheduleExamOptional = scheduleExamDao.getByFinishOnAndStartAt(
				data.getStartAt().atStartOfDay(), data.getFinishOn().atStartOfDay(), data.getStudyClassId());
		if (scheduleExamOptional.isPresent()) {
			throw new RuntimeException("Duplicate Schedule!");
		} else {
			examInsert.setExamCode(data.getExamCode());
			examInsert.setExamName(data.getExamName());
			Optional<StudyClass> studyClassOptional = studyClassDao.getById(data.getStudyClassId());
			examInsert.setStudyClassId(studyClassOptional.get());
			examInsert.setCreatedBy(principalService.getPrincipal().getId());
			examInsert = examDao.insert(examInsert);
			if (data.getFile() != null) {
				for (int i = 0; i < data.getFile().size(); i++) {
					final AttachmentExam attachmentExam = new AttachmentExam();
					File file = new File();
					file.setFileName(data.getFile().get(i).getFile());
					file.setExtension(data.getFile().get(i).getExtension());
					file.setCreatedBy(principalService.getPrincipal().getId());
					file = fileDao.insert(file);
					attachmentExam.setFileId(file);
					attachmentExam.setExamId(examInsert);
					attachmentExam.setCreatedBy(principalService.getPrincipal().getId());
					attachmentExamDao.insert(attachmentExam);
				}
			}
			final ScheduleExam scheduleExam = new ScheduleExam();
			scheduleExam.setStartAt(data.getStartAt().atStartOfDay());
			scheduleExam.setFinishOn(data.getFinishOn().atStartOfDay());
			scheduleExam.setExamId(examInsert);
			scheduleExam.setCreatedBy(principalService.getPrincipal().getId());
			scheduleExamDao.insert(scheduleExam);

			final ExamInsertResDataDto examInsertResDataDto = new ExamInsertResDataDto();
			examInsertResDataDto.setId(examInsert.getId());

			final ExamInsertResDto examInsertResDto = new ExamInsertResDto();
			examInsertResDto.setData(examInsertResDataDto);
			examInsertResDto.setMessage(MessageEnum.INSERTED.toString());
			return examInsertResDto;
		}
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
