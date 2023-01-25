package com.lawencon.e.learning.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.e.learning.constant.MessageEnum;
import com.lawencon.e.learning.dao.AttachmentTheoryDao;
import com.lawencon.e.learning.dao.AttendanceTheoryDao;
import com.lawencon.e.learning.dao.ScheduleTheoryDao;
import com.lawencon.e.learning.dao.StudyClassDetailDao;
import com.lawencon.e.learning.dao.UserDao;
import com.lawencon.e.learning.dto.attendancetheory.AttendanceTheoryDataDto;
import com.lawencon.e.learning.dto.attendancetheory.AttendanceTheoryDto;
import com.lawencon.e.learning.dto.attendancetheory.AttendanceTheoryInsertReqDataDto;
import com.lawencon.e.learning.dto.attendancetheory.AttendanceTheoryInsertResDataDto;
import com.lawencon.e.learning.dto.attendancetheory.AttendanceTheoryInsertResDto;
import com.lawencon.e.learning.dto.attendancetheory.AttendanceTheoryUpdateReqDataDto;
import com.lawencon.e.learning.dto.attendancetheory.AttendanceTheoryUpdateResDataDto;
import com.lawencon.e.learning.dto.attendancetheory.AttendanceTheoryUpdateResDto;
import com.lawencon.e.learning.dto.attendancetheory.AttendanceTheorysDto;
import com.lawencon.e.learning.model.AttachmentTheory;
import com.lawencon.e.learning.model.AttendanceTheory;
import com.lawencon.e.learning.model.ScheduleTheory;
import com.lawencon.e.learning.model.StudyClassDetail;
import com.lawencon.e.learning.model.User;
import com.lawencon.e.learning.service.AttendanceTheoryService;
import com.lawencon.e.learning.service.PrincipalService;

@Service
public class AttendanceTheoryServiceImpl implements AttendanceTheoryService {

	private final AttendanceTheoryDao attendanceTheoryDao;
	private final ScheduleTheoryDao scheduleTheoryDao;
	private final StudyClassDetailDao studyClassDetailDao;
	private final UserDao userDao;
	private final PrincipalService principalService;
	private final AttachmentTheoryDao attachmentTheoryDao;
	public AttendanceTheoryServiceImpl(AttendanceTheoryDao attendanceTheoryDao, ScheduleTheoryDao scheduleTheoryDao,
			StudyClassDetailDao studyClassDetailDao, UserDao userDao, PrincipalService principalService, AttachmentTheoryDao attachmentTheoryDao) {
		this.attendanceTheoryDao = attendanceTheoryDao;
		this.scheduleTheoryDao = scheduleTheoryDao;
		this.studyClassDetailDao = studyClassDetailDao;
		this.userDao = userDao;
		this.principalService = principalService;
		this.attachmentTheoryDao = attachmentTheoryDao;
	}

	@Override
	public AttendanceTheorysDto getAllbyStudentSide(Long studyClassDetailId) {
		final List<AttendanceTheory> attendanceTheorys = attendanceTheoryDao.getAllbyStudentSide(studyClassDetailId);
		final List<AttendanceTheoryDataDto> attendanceTheoryDataDtos = new ArrayList<>();
		final List<Long>fileId = new ArrayList<>();
		for (int i = 0; i < attendanceTheorys.size(); i++) {
			final AttendanceTheoryDataDto attendanceTheoryDataDto = new AttendanceTheoryDataDto();
			final List<AttachmentTheory>attachmentTheories = attachmentTheoryDao.getByTheoryId(attendanceTheorys.get(i).getScheduleId().getTheoryId().getId());
			for(int j = 0; j<attachmentTheories.size(); j++) {
				fileId.add(attachmentTheories.get(j).getFileId().getId());
			}
			attendanceTheoryDataDto.setFileId(fileId);
			attendanceTheoryDataDto.setStudentName(attendanceTheorys.get(i).getStudentId().getFullNameUser());
			attendanceTheoryDataDto.setApprove(attendanceTheorys.get(i).getApprove());
			attendanceTheoryDataDto.setAttendTime(attendanceTheorys.get(i).getAttendTime());
			attendanceTheoryDataDto
					.setTheoryName(attendanceTheorys.get(i).getScheduleId().getTheoryId().getTheoryName());
			attendanceTheoryDataDto.setId(attendanceTheorys.get(i).getId());
			attendanceTheoryDataDto.setVer(attendanceTheorys.get(i).getVer());
			attendanceTheoryDataDto.setStartTheory(attendanceTheorys.get(i).getScheduleId().getStartAt());
			attendanceTheoryDataDto.setFinishTheory(attendanceTheorys.get(i).getScheduleId().getFinishOn());
			attendanceTheoryDataDto.setScheduleId(attendanceTheorys.get(i).getScheduleId().getId());
			attendanceTheoryDataDtos.add(attendanceTheoryDataDto);
		}
		final AttendanceTheorysDto attendanceTheorysDto = new AttendanceTheorysDto();
		attendanceTheorysDto.setData(attendanceTheoryDataDtos);

		return attendanceTheorysDto;
	}

	@Override
	public AttendanceTheorysDto getAllbyTeacherSide(Long studyClassId) {
		final List<AttendanceTheory> attendanceTheorys = attendanceTheoryDao.getAllbyTeacherSide(studyClassId);
		final List<AttendanceTheoryDataDto> attendanceTheoryDataDtos = new ArrayList<>();
		for (int i = 0; i < attendanceTheorys.size(); i++) {
			final AttendanceTheoryDataDto attendanceTheoryDataDto = new AttendanceTheoryDataDto();
			attendanceTheoryDataDto.setStudentName(attendanceTheorys.get(i).getStudentId().getFullNameUser());
			attendanceTheoryDataDto.setApprove(attendanceTheorys.get(i).getApprove());
			attendanceTheoryDataDto.setAttendTime(attendanceTheorys.get(i).getAttendTime());
			attendanceTheoryDataDto
					.setTheoryName(attendanceTheorys.get(i).getScheduleId().getTheoryId().getTheoryName());
			attendanceTheoryDataDto.setId(attendanceTheorys.get(i).getId());
			attendanceTheoryDataDto.setVer(attendanceTheorys.get(i).getVer());
			attendanceTheoryDataDto.setStartTheory(attendanceTheorys.get(i).getScheduleId().getStartAt());
			attendanceTheoryDataDto.setFinishTheory(attendanceTheorys.get(i).getScheduleId().getFinishOn());
			attendanceTheoryDataDto.setScheduleId(attendanceTheorys.get(i).getScheduleId().getId());
			attendanceTheoryDataDtos.add(attendanceTheoryDataDto);
		}
		final AttendanceTheorysDto attendanceTheorysDto = new AttendanceTheorysDto();
		attendanceTheorysDto.setData(attendanceTheoryDataDtos);

		return attendanceTheorysDto;
	}

	@Override
	public AttendanceTheoryDto getById(Long id) {
		final Optional<AttendanceTheory> attendanceTheoryOptional = attendanceTheoryDao.getById(id);
		final AttendanceTheoryDataDto attendanceTheoryDataDto = new AttendanceTheoryDataDto();
		if (attendanceTheoryOptional.isPresent()) {
			attendanceTheoryDataDto.setStudentName(attendanceTheoryOptional.get().getStudentId().getFullNameUser());
			attendanceTheoryDataDto.setApprove(attendanceTheoryOptional.get().getApprove());
			attendanceTheoryDataDto.setAttendTime(attendanceTheoryOptional.get().getAttendTime());
			attendanceTheoryDataDto
					.setTheoryName(attendanceTheoryOptional.get().getScheduleId().getTheoryId().getTheoryName());
			attendanceTheoryDataDto.setId(attendanceTheoryOptional.get().getId());
			attendanceTheoryDataDto.setVer(attendanceTheoryOptional.get().getVer());
			attendanceTheoryDataDto.setStartTheory(attendanceTheoryOptional.get().getScheduleId().getStartAt());
			attendanceTheoryDataDto.setFinishTheory(attendanceTheoryOptional.get().getScheduleId().getFinishOn());
			attendanceTheoryDataDto.setScheduleId(attendanceTheoryOptional.get().getScheduleId().getId());
		}

		final AttendanceTheoryDto attendanceTheoryDto = new AttendanceTheoryDto();
		attendanceTheoryDto.setData(attendanceTheoryDataDto);
		return attendanceTheoryDto;
	}

	@Transactional
	@Override
	public AttendanceTheoryInsertResDto insert(AttendanceTheoryInsertReqDataDto data) {
		final Optional<AttendanceTheory> attendanceTheoryOptional = attendanceTheoryDao
				.getByStudentIdAndScheduleId(principalService.getPrincipal().getId(), data.getScheduleId());
		AttendanceTheory attendanceTheoryInsert = new AttendanceTheory();
		if (!attendanceTheoryOptional.isPresent()) {
			Optional<ScheduleTheory> scheduleTheoryOptional = Optional.ofNullable(null);
			scheduleTheoryOptional = scheduleTheoryDao.getById(data.getScheduleId());
			final Optional<StudyClassDetail> studyClassDetailOptional = studyClassDetailDao
					.getById(data.getStudyClassDetailId());
			final Optional<User> userOptional = userDao.getById(principalService.getPrincipal().getId());

			attendanceTheoryInsert.setStudentId(userOptional.get());
			attendanceTheoryInsert.setScheduleId(scheduleTheoryOptional.get());
			attendanceTheoryInsert.setStudyClassDetailId(studyClassDetailOptional.get());
			attendanceTheoryInsert.setApprove(false);
			attendanceTheoryInsert.setAttendTime(LocalDate.now());
			attendanceTheoryInsert.setCreatedBy(principalService.getPrincipal().getId());
			attendanceTheoryInsert = attendanceTheoryDao.insert(attendanceTheoryInsert);

			AttendanceTheoryInsertResDataDto attendanceTheoryInsertResDataDto = new AttendanceTheoryInsertResDataDto();
			attendanceTheoryInsertResDataDto.setId(attendanceTheoryInsert.getId());

			AttendanceTheoryInsertResDto attendanceTheoryInsertResDto = new AttendanceTheoryInsertResDto();
			attendanceTheoryInsertResDto.setData(attendanceTheoryInsertResDataDto);
			attendanceTheoryInsertResDto.setMessage(MessageEnum.INSERTED.toString());
			return attendanceTheoryInsertResDto;
		} else {
			throw new RuntimeException("Attendance Can Only Be Once");
		}
	}

	@Transactional
	@Override
	public AttendanceTheoryUpdateResDto approving(AttendanceTheoryUpdateReqDataDto data) {
		AttendanceTheory attendanceTheoryUpdated = new AttendanceTheory();
		final Optional<AttendanceTheory> attendanceTheoryOptional = attendanceTheoryDao.getById(data.getId());
		attendanceTheoryUpdated = attendanceTheoryOptional.get();
		attendanceTheoryUpdated.setApprove(true);
		attendanceTheoryUpdated.setUpdatedBy(principalService.getPrincipal().getId());
		attendanceTheoryUpdated = attendanceTheoryDao.approving(attendanceTheoryUpdated);

		final AttendanceTheoryUpdateResDataDto attendanceTheoryUpdateResDataDto = new AttendanceTheoryUpdateResDataDto();
		attendanceTheoryUpdateResDataDto.setVer(attendanceTheoryUpdated.getVer());

		final AttendanceTheoryUpdateResDto attendanceTheoryUpdateResDto = new AttendanceTheoryUpdateResDto();
		attendanceTheoryUpdateResDto.setData(attendanceTheoryUpdateResDataDto);
		attendanceTheoryUpdateResDto.setMessage(MessageEnum.UPDATED.toString());
		return attendanceTheoryUpdateResDto;
	}
}
