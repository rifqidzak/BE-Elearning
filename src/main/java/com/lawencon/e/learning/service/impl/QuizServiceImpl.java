package com.lawencon.e.learning.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.lawencon.e.learning.constant.MessageEnum;
import com.lawencon.e.learning.dao.AttachmentQuizDao;
import com.lawencon.e.learning.dao.FileDao;
import com.lawencon.e.learning.dao.QuizDao;
import com.lawencon.e.learning.dao.ScheduleQuizDao;
import com.lawencon.e.learning.dao.StudyClassDao;
import com.lawencon.e.learning.dto.quiz.QuizInsertReqDataDto;
import com.lawencon.e.learning.dto.quiz.QuizInsertResDataDto;
import com.lawencon.e.learning.dto.quiz.QuizInsertResDto;
import com.lawencon.e.learning.model.AttachmentQuiz;
import com.lawencon.e.learning.model.File;
import com.lawencon.e.learning.model.Quiz;
import com.lawencon.e.learning.model.ScheduleQuiz;
import com.lawencon.e.learning.model.StudyClass;
import com.lawencon.e.learning.service.PrincipalService;
import com.lawencon.e.learning.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

	private final QuizDao quizDao;
	private final FileDao fileDao;
	private final AttachmentQuizDao attachmentQuizDao;
	private final ScheduleQuizDao scheduleQuizDao;
	private final StudyClassDao studyClassDao;
	private final PrincipalService principalService;

	public QuizServiceImpl(SessionFactory sessionFactory, QuizDao quizDao, FileDao fileDao,
			AttachmentQuizDao attachmentQuizDao, ScheduleQuizDao scheduleQuizDao, StudyClassDao studyClassDao,
			PrincipalService principalService) {
		this.quizDao = quizDao;
		this.fileDao = fileDao;
		this.attachmentQuizDao = attachmentQuizDao;
		this.scheduleQuizDao = scheduleQuizDao;
		this.studyClassDao = studyClassDao;
		this.principalService = principalService;
	}

	@Transactional
	@Override
	public QuizInsertResDto insert(QuizInsertReqDataDto data) {
		Quiz quizInsert = new Quiz();

		final Optional<ScheduleQuiz> scheduleQuizOptional = scheduleQuizDao.getByFinishOnAndStartAt(
				data.getStartAt().atStartOfDay(), data.getFinishOn().atStartOfDay(), data.getStudyClassId());
		if (scheduleQuizOptional.isPresent()) {
			throw new RuntimeException("Duplicate Schedule!");
		} else {
			quizInsert.setQuizCode(data.getQuizCode());
			quizInsert.setQuizName(data.getQuizName());
			Optional<StudyClass> studyClassOptional = studyClassDao.getById(data.getStudyClassId());
			quizInsert.setStudyClassId(studyClassOptional.get());
			quizInsert.setCreatedBy(principalService.getPrincipal().getId());
			quizInsert = quizDao.insert(quizInsert);
			if (data.getFile() != null) {
				for (int i = 0; i < data.getFile().size(); i++) {
					final AttachmentQuiz attachmentQuiz = new AttachmentQuiz();
					File file = new File();
					file.setFileName(data.getFile().get(i).getFile());
					file.setExtension(data.getFile().get(i).getExtension());
					file.setCreatedBy(principalService.getPrincipal().getId());
					file = fileDao.insert(file);
					attachmentQuiz.setFileId(file);
					attachmentQuiz.setQuizId(quizInsert);
					attachmentQuiz.setCreatedBy(principalService.getPrincipal().getId());
					attachmentQuizDao.insert(attachmentQuiz);
				}
			}
			final ScheduleQuiz scheduleQuiz = new ScheduleQuiz();
			scheduleQuiz.setStartAt(data.getStartAt().atStartOfDay());
			scheduleQuiz.setFinishOn(data.getFinishOn().atStartOfDay());
			scheduleQuiz.setQuizId(quizInsert);
			scheduleQuiz.setCreatedBy(principalService.getPrincipal().getId());
			scheduleQuizDao.insert(scheduleQuiz);

			final QuizInsertResDataDto quizInsertResDataDto = new QuizInsertResDataDto();
			quizInsertResDataDto.setId(quizInsert.getId());

			final QuizInsertResDto quizInsertResDto = new QuizInsertResDto();
			quizInsertResDto.setData(quizInsertResDataDto);
			quizInsertResDto.setMessage(MessageEnum.INSERTED.toString());
			return quizInsertResDto;
		}
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
