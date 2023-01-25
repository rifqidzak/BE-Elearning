package com.lawencon.e.learning.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.e.learning.constant.MessageEnum;
import com.lawencon.e.learning.dao.AttachmentQuizDao;
import com.lawencon.e.learning.dao.AttendanceQuizDao;
import com.lawencon.e.learning.dao.ScheduleQuizDao;
import com.lawencon.e.learning.dao.StudyClassDetailDao;
import com.lawencon.e.learning.dao.UserDao;
import com.lawencon.e.learning.dto.attendancequiz.AttendanceQuizDataDto;
import com.lawencon.e.learning.dto.attendancequiz.AttendanceQuizDto;
import com.lawencon.e.learning.dto.attendancequiz.AttendanceQuizInsertReqDataDto;
import com.lawencon.e.learning.dto.attendancequiz.AttendanceQuizInsertResDataDto;
import com.lawencon.e.learning.dto.attendancequiz.AttendanceQuizInsertResDto;
import com.lawencon.e.learning.dto.attendancequiz.AttendanceQuizUpdateReqDataDto;
import com.lawencon.e.learning.dto.attendancequiz.AttendanceQuizUpdateResDataDto;
import com.lawencon.e.learning.dto.attendancequiz.AttendanceQuizUpdateResDto;
import com.lawencon.e.learning.dto.attendancequiz.AttendanceQuizsDto;
import com.lawencon.e.learning.model.AttachmentQuiz;
import com.lawencon.e.learning.model.AttendanceQuiz;
import com.lawencon.e.learning.model.ScheduleQuiz;
import com.lawencon.e.learning.model.StudyClassDetail;
import com.lawencon.e.learning.model.User;
import com.lawencon.e.learning.service.AttendanceQuizService;
import com.lawencon.e.learning.service.PrincipalService;

@Service
public class AttendanceQuizServiceImpl implements AttendanceQuizService {
	private final AttendanceQuizDao attendanceQuizDao;
	private final ScheduleQuizDao scheduleQuizDao;
	private final StudyClassDetailDao studyClassDetailDao;
	private final UserDao userDao;
	private final PrincipalService principalService;
	private final AttachmentQuizDao attachmentQuizDao;
	public AttendanceQuizServiceImpl(AttendanceQuizDao attendanceQuizDao, ScheduleQuizDao scheduleQuizDao,
			StudyClassDetailDao studyClassDetailDao, UserDao userDao, PrincipalService principalService, AttachmentQuizDao attachmentQuizDao) {
		this.attendanceQuizDao = attendanceQuizDao;
		this.scheduleQuizDao = scheduleQuizDao;
		this.studyClassDetailDao = studyClassDetailDao;
		this.userDao = userDao;
		this.principalService = principalService;
		this.attachmentQuizDao = attachmentQuizDao;
	}

	@Override
	public AttendanceQuizsDto getAllbyStudentSide(Long studyClassDetailId) {
		final List<AttendanceQuiz> attendanceQuizs = attendanceQuizDao.getAllbyStudentSide(studyClassDetailId);
		final List<AttendanceQuizDataDto>attendanceQuizDataDtos = new ArrayList<>();
		final List<Long>fileId = new ArrayList<>();
		for(int i = 0; i<attendanceQuizs.size();i++) {
			final AttendanceQuizDataDto attendanceQuizDataDto = new AttendanceQuizDataDto();
			final List<AttachmentQuiz>attachmentQuizs = attachmentQuizDao.getByQuizId(attendanceQuizs.get(i).getScheduleId().getQuizId().getId());
			for(int j = 0; j<attachmentQuizs.size(); j++) {
				fileId.add(attachmentQuizs.get(j).getFileId().getId());
			}
			attendanceQuizDataDto.setFileId(fileId);
			attendanceQuizDataDto.setStudentName(attendanceQuizs.get(i).getStudentId().getFullNameUser());
			attendanceQuizDataDto.setApprove(attendanceQuizs.get(i).getApprove());
			attendanceQuizDataDto.setAttendTime(attendanceQuizs.get(i).getAttendTime());
			attendanceQuizDataDto.setQuizName(attendanceQuizs.get(i).getScheduleId().getQuizId().getQuizName());
			attendanceQuizDataDto.setId(attendanceQuizs.get(i).getId());
			attendanceQuizDataDto.setVer(attendanceQuizs.get(i).getVer());
			attendanceQuizDataDto.setStartQuiz(attendanceQuizs.get(i).getScheduleId().getStartAt());
			attendanceQuizDataDto.setFinishQuiz(attendanceQuizs.get(i).getScheduleId().getFinishOn());
			attendanceQuizDataDto.setScheduleId(attendanceQuizs.get(i).getScheduleId().getId());
			attendanceQuizDataDtos.add(attendanceQuizDataDto);
		}
		final AttendanceQuizsDto attendanceQuizsDto = new AttendanceQuizsDto();
		attendanceQuizsDto.setData(attendanceQuizDataDtos);
		
		return attendanceQuizsDto;
	}

	@Override
	public AttendanceQuizsDto getAllbyTeacherSide(Long studyClassId) {
		final List<AttendanceQuiz> attendanceQuizs = attendanceQuizDao.getAllbyTeacherSide(studyClassId);
		final List<AttendanceQuizDataDto>attendanceQuizDataDtos = new ArrayList<>();
		for(int i = 0; i<attendanceQuizs.size();i++) {
			final AttendanceQuizDataDto attendanceQuizDataDto = new AttendanceQuizDataDto();
			attendanceQuizDataDto.setStudentName(attendanceQuizs.get(i).getStudentId().getFullNameUser());
			attendanceQuizDataDto.setApprove(attendanceQuizs.get(i).getApprove());
			attendanceQuizDataDto.setAttendTime(attendanceQuizs.get(i).getAttendTime());
			attendanceQuizDataDto.setQuizName(attendanceQuizs.get(i).getScheduleId().getQuizId().getQuizName());
			attendanceQuizDataDto.setId(attendanceQuizs.get(i).getId());
			attendanceQuizDataDto.setVer(attendanceQuizs.get(i).getVer());
			attendanceQuizDataDto.setStartQuiz(attendanceQuizs.get(i).getScheduleId().getStartAt());
			attendanceQuizDataDto.setFinishQuiz(attendanceQuizs.get(i).getScheduleId().getFinishOn());
			attendanceQuizDataDto.setScheduleId(attendanceQuizs.get(i).getScheduleId().getId());
			attendanceQuizDataDtos.add(attendanceQuizDataDto);
		}
		final AttendanceQuizsDto attendanceQuizsDto = new AttendanceQuizsDto();
		attendanceQuizsDto.setData(attendanceQuizDataDtos);
		
		return attendanceQuizsDto;
	}

	@Override
	public AttendanceQuizDto getById(Long id) {
		final Optional<AttendanceQuiz> attendanceQuizOptional = attendanceQuizDao.getById(id);
		final AttendanceQuizDataDto attendanceQuizDataDto = new AttendanceQuizDataDto();
		if(attendanceQuizOptional.isPresent()) {
			attendanceQuizDataDto.setStudentName(attendanceQuizOptional.get().getStudentId().getFullNameUser());
			attendanceQuizDataDto.setApprove(attendanceQuizOptional.get().getApprove());
			attendanceQuizDataDto.setAttendTime(attendanceQuizOptional.get().getAttendTime());
			attendanceQuizDataDto.setQuizName(attendanceQuizOptional.get().getScheduleId().getQuizId().getQuizName());
			attendanceQuizDataDto.setId(attendanceQuizOptional.get().getId());
			attendanceQuizDataDto.setVer(attendanceQuizOptional.get().getVer());
			attendanceQuizDataDto.setStartQuiz(attendanceQuizOptional.get().getScheduleId().getStartAt());
			attendanceQuizDataDto.setFinishQuiz(attendanceQuizOptional.get().getScheduleId().getFinishOn());
			attendanceQuizDataDto.setScheduleId(attendanceQuizOptional.get().getScheduleId().getId());
		}
		
		final AttendanceQuizDto attendanceQuizDto = new AttendanceQuizDto();
		attendanceQuizDto.setData(attendanceQuizDataDto);
		return attendanceQuizDto;
	}

	@Transactional
	@Override
	public AttendanceQuizInsertResDto insert(AttendanceQuizInsertReqDataDto data) {
		final Optional<AttendanceQuiz> attendanceQuizOptional = attendanceQuizDao
				.getByStudentIdAndScheduleId(principalService.getPrincipal().getId(), data.getScheduleId());
		AttendanceQuiz attendanceQuizInsert = new AttendanceQuiz();
		if (!attendanceQuizOptional.isPresent()) {
			Optional<ScheduleQuiz> scheduleQuizOptional = Optional.ofNullable(null);
			scheduleQuizOptional = scheduleQuizDao.getById(data.getScheduleId());
			final Optional<StudyClassDetail> studyClassDetailOptional = studyClassDetailDao
					.getById(data.getStudyClassDetailId());
			final Optional<User> userOptional = userDao.getById(principalService.getPrincipal().getId());

			attendanceQuizInsert.setStudentId(userOptional.get());
			attendanceQuizInsert.setScheduleId(scheduleQuizOptional.get());
			attendanceQuizInsert.setStudyClassDetailId(studyClassDetailOptional.get());
			attendanceQuizInsert.setApprove(false);
			attendanceQuizInsert.setAttendTime(LocalDate.now());
			attendanceQuizInsert.setCreatedBy(principalService.getPrincipal().getId());
			attendanceQuizInsert = attendanceQuizDao.insert(attendanceQuizInsert);

			AttendanceQuizInsertResDataDto attendanceQuizInsertResDataDto = new AttendanceQuizInsertResDataDto();
			attendanceQuizInsertResDataDto.setId(attendanceQuizInsert.getId());

			AttendanceQuizInsertResDto attendanceQuizInsertResDto = new AttendanceQuizInsertResDto();
			attendanceQuizInsertResDto.setData(attendanceQuizInsertResDataDto);
			attendanceQuizInsertResDto.setMessage(MessageEnum.INSERTED.toString());
			return attendanceQuizInsertResDto;
		} else {
			throw new RuntimeException("Attendance Can Only Be Once");
		}
	}

	@Transactional
	@Override
	public AttendanceQuizUpdateResDto approving(AttendanceQuizUpdateReqDataDto data) {
		AttendanceQuiz attendanceQuizUpdated = new AttendanceQuiz();
		final Optional<AttendanceQuiz> attendanceQuizOptional = attendanceQuizDao.getById(data.getId());
		attendanceQuizUpdated = attendanceQuizOptional.get();
		attendanceQuizUpdated.setApprove(true);
		attendanceQuizUpdated.setUpdatedBy(principalService.getPrincipal().getId());
		attendanceQuizUpdated = attendanceQuizDao.approving(attendanceQuizUpdated);

		final AttendanceQuizUpdateResDataDto attendanceQuizUpdateResDataDto = new AttendanceQuizUpdateResDataDto();
		attendanceQuizUpdateResDataDto.setVer(attendanceQuizUpdated.getVer());

		final AttendanceQuizUpdateResDto attendanceQuizUpdateResDto = new AttendanceQuizUpdateResDto();
		attendanceQuizUpdateResDto.setData(attendanceQuizUpdateResDataDto);
		attendanceQuizUpdateResDto.setMessage(MessageEnum.UPDATED.toString());
		return attendanceQuizUpdateResDto;
	}

}
