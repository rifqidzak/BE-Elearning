package com.lawencon.e.learning.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.e.learning.constant.MessageEnum;
import com.lawencon.e.learning.dao.AttachmentExamDao;
import com.lawencon.e.learning.dao.AttendanceExamDao;
import com.lawencon.e.learning.dao.ScheduleExamDao;
import com.lawencon.e.learning.dao.StudyClassDetailDao;
import com.lawencon.e.learning.dao.UserDao;
import com.lawencon.e.learning.dto.attendanceexam.AttendanceExamDataDto;
import com.lawencon.e.learning.dto.attendanceexam.AttendanceExamDto;
import com.lawencon.e.learning.dto.attendanceexam.AttendanceExamInsertReqDataDto;
import com.lawencon.e.learning.dto.attendanceexam.AttendanceExamInsertResDataDto;
import com.lawencon.e.learning.dto.attendanceexam.AttendanceExamInsertResDto;
import com.lawencon.e.learning.dto.attendanceexam.AttendanceExamUpdateReqDataDto;
import com.lawencon.e.learning.dto.attendanceexam.AttendanceExamUpdateResDataDto;
import com.lawencon.e.learning.dto.attendanceexam.AttendanceExamUpdateResDto;
import com.lawencon.e.learning.dto.attendanceexam.AttendanceExamsDto;
import com.lawencon.e.learning.model.AttachmentExam;
import com.lawencon.e.learning.model.AttendanceExam;
import com.lawencon.e.learning.model.ScheduleExam;
import com.lawencon.e.learning.model.StudyClassDetail;
import com.lawencon.e.learning.model.User;
import com.lawencon.e.learning.service.AttendanceExamService;
import com.lawencon.e.learning.service.PrincipalService;

@Service
public class AttendanceExamServiceImpl implements AttendanceExamService {
	private final AttendanceExamDao attendanceExamDao;
	private final ScheduleExamDao scheduleExamDao;
	private final StudyClassDetailDao studyClassDetailDao;
	private final AttachmentExamDao attachmentExamDao;
	private final UserDao userDao;
	private final PrincipalService principalService;
	public AttendanceExamServiceImpl(AttendanceExamDao attendanceExamDao, ScheduleExamDao scheduleExamDao,
			StudyClassDetailDao studyClassDetailDao, UserDao userDao, PrincipalService principalService, AttachmentExamDao attachmentExamDao) {
		this.attendanceExamDao = attendanceExamDao;
		this.scheduleExamDao = scheduleExamDao;
		this.studyClassDetailDao = studyClassDetailDao;
		this.userDao = userDao;
		this.principalService = principalService;
		this.attachmentExamDao = attachmentExamDao;
	}

	@Override
	public AttendanceExamsDto getAllbyStudentSide(Long studyClassDetailId) {
		final List<AttendanceExam> attendanceExams = attendanceExamDao.getAllbyStudentSide(studyClassDetailId);
		final List<AttendanceExamDataDto>attendanceExamDataDtos = new ArrayList<>();
		final List<Long>fileId = new ArrayList<>();
		for(int i = 0; i<attendanceExams.size();i++) {
			final AttendanceExamDataDto attendanceExamDataDto = new AttendanceExamDataDto();
			final List<AttachmentExam>attachmentExams = attachmentExamDao.getByExamId(attendanceExams.get(i).getScheduleId().getExamId().getId());
			for(int j = 0; j<attachmentExams.size(); j++) {
				fileId.add(attachmentExams.get(j).getFileId().getId());
			}
			attendanceExamDataDto.setFileId(fileId);
			attendanceExamDataDto.setStudentName(attendanceExams.get(i).getStudentId().getFullNameUser());
			attendanceExamDataDto.setApprove(attendanceExams.get(i).getApprove());
			attendanceExamDataDto.setAttendTime(attendanceExams.get(i).getAttendTime());
			attendanceExamDataDto.setExamName(attendanceExams.get(i).getScheduleId().getExamId().getExamName());
			attendanceExamDataDto.setId(attendanceExams.get(i).getId());
			attendanceExamDataDto.setVer(attendanceExams.get(i).getVer());
			attendanceExamDataDto.setStartExam(attendanceExams.get(i).getScheduleId().getStartAt());
			attendanceExamDataDto.setFinishExam(attendanceExams.get(i).getScheduleId().getFinishOn());
			attendanceExamDataDto.setScheduleId(attendanceExams.get(i).getScheduleId().getId());
			attendanceExamDataDtos.add(attendanceExamDataDto);
		}
		final AttendanceExamsDto attendanceExamsDto = new AttendanceExamsDto();
		attendanceExamsDto.setData(attendanceExamDataDtos);
		
		return attendanceExamsDto;
	}

	@Override
	public AttendanceExamsDto getAllbyTeacherSide(Long studyClassId) {
		final List<AttendanceExam> attendanceExams = attendanceExamDao.getAllbyTeacherSide(studyClassId);
		final List<AttendanceExamDataDto>attendanceExamDataDtos = new ArrayList<>();
		for(int i = 0; i<attendanceExams.size();i++) {
			final AttendanceExamDataDto attendanceExamDataDto = new AttendanceExamDataDto();
			attendanceExamDataDto.setStudentName(attendanceExams.get(i).getStudentId().getFullNameUser());
			attendanceExamDataDto.setApprove(attendanceExams.get(i).getApprove());
			attendanceExamDataDto.setAttendTime(attendanceExams.get(i).getAttendTime());
			attendanceExamDataDto.setExamName(attendanceExams.get(i).getScheduleId().getExamId().getExamName());
			attendanceExamDataDto.setId(attendanceExams.get(i).getId());
			attendanceExamDataDto.setVer(attendanceExams.get(i).getVer());
			attendanceExamDataDto.setStartExam(attendanceExams.get(i).getScheduleId().getStartAt());
			attendanceExamDataDto.setFinishExam(attendanceExams.get(i).getScheduleId().getFinishOn());
			attendanceExamDataDto.setScheduleId(attendanceExams.get(i).getScheduleId().getId());
			attendanceExamDataDtos.add(attendanceExamDataDto);
		}
		final AttendanceExamsDto attendanceExamsDto = new AttendanceExamsDto();
		attendanceExamsDto.setData(attendanceExamDataDtos);
		
		return attendanceExamsDto;
	}

	@Override
	public AttendanceExamDto getById(Long id) {
		final Optional<AttendanceExam> attendanceExamOptional = attendanceExamDao.getById(id);
		final AttendanceExamDataDto attendanceExamDataDto = new AttendanceExamDataDto();
		if(attendanceExamOptional.isPresent()) {
			attendanceExamDataDto.setStudentName(attendanceExamOptional.get().getStudentId().getFullNameUser());
			attendanceExamDataDto.setApprove(attendanceExamOptional.get().getApprove());
			attendanceExamDataDto.setAttendTime(attendanceExamOptional.get().getAttendTime());
			attendanceExamDataDto.setExamName(attendanceExamOptional.get().getScheduleId().getExamId().getExamName());
			attendanceExamDataDto.setId(attendanceExamOptional.get().getId());
			attendanceExamDataDto.setVer(attendanceExamOptional.get().getVer());
			attendanceExamDataDto.setStartExam(attendanceExamOptional.get().getScheduleId().getStartAt());
			attendanceExamDataDto.setFinishExam(attendanceExamOptional.get().getScheduleId().getFinishOn());
			attendanceExamDataDto.setScheduleId(attendanceExamOptional.get().getScheduleId().getId());
		}
		
		final AttendanceExamDto attendanceExamDto = new AttendanceExamDto();
		attendanceExamDto.setData(attendanceExamDataDto);
		return attendanceExamDto;
	}

	@Transactional
	@Override
	public AttendanceExamInsertResDto insert(AttendanceExamInsertReqDataDto data) {
		final Optional<AttendanceExam> attendanceExamOptional = attendanceExamDao
				.getByStudentIdAndScheduleId(principalService.getPrincipal().getId(), data.getScheduleId());
		AttendanceExam attendanceExamInsert = new AttendanceExam();
		if (!attendanceExamOptional.isPresent()) {
			Optional<ScheduleExam> scheduleExamOptional = Optional.ofNullable(null);
			scheduleExamOptional = scheduleExamDao.getById(data.getScheduleId());
			final Optional<StudyClassDetail> studyClassDetailOptional = studyClassDetailDao
					.getById(data.getStudyClassDetailId());
			final Optional<User> userOptional = userDao.getById(principalService.getPrincipal().getId());

			attendanceExamInsert.setStudentId(userOptional.get());
			attendanceExamInsert.setScheduleId(scheduleExamOptional.get());
			attendanceExamInsert.setStudyClassDetailId(studyClassDetailOptional.get());
			attendanceExamInsert.setApprove(false);
			attendanceExamInsert.setAttendTime(LocalDate.now());
			attendanceExamInsert.setCreatedBy(principalService.getPrincipal().getId());
			attendanceExamInsert = attendanceExamDao.insert(attendanceExamInsert);

			AttendanceExamInsertResDataDto attendanceExamInsertResDataDto = new AttendanceExamInsertResDataDto();
			attendanceExamInsertResDataDto.setId(attendanceExamInsert.getId());

			AttendanceExamInsertResDto attendanceExamInsertResDto = new AttendanceExamInsertResDto();
			attendanceExamInsertResDto.setData(attendanceExamInsertResDataDto);;
			attendanceExamInsertResDto.setMessage(MessageEnum.INSERTED.toString());
			return attendanceExamInsertResDto;
		} else {
			throw new RuntimeException("Attendance Can Only Be Once");
		}
	}

	@Transactional
	@Override
	public AttendanceExamUpdateResDto approving(AttendanceExamUpdateReqDataDto data) {
		AttendanceExam attendanceExamUpdated = new AttendanceExam();
		final Optional<AttendanceExam> attendanceExamOptional = attendanceExamDao.getById(data.getId());
		attendanceExamUpdated = attendanceExamOptional.get();
		attendanceExamUpdated.setApprove(true);
		attendanceExamUpdated.setUpdatedBy(principalService.getPrincipal().getId());
		attendanceExamUpdated = attendanceExamDao.approving(attendanceExamUpdated);

		final AttendanceExamUpdateResDataDto attendanceExamUpdateResDataDto = new AttendanceExamUpdateResDataDto();
		attendanceExamUpdateResDataDto.setVer(attendanceExamUpdated.getVer());

		final AttendanceExamUpdateResDto attendanceExamUpdateResDto = new AttendanceExamUpdateResDto();
		attendanceExamUpdateResDto.setData(attendanceExamUpdateResDataDto);
		attendanceExamUpdateResDto.setMessage(MessageEnum.UPDATED.toString());
		return attendanceExamUpdateResDto;
	}

}
