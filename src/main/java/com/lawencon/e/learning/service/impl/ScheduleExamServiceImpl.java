package com.lawencon.e.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lawencon.e.learning.dao.ScheduleExamDao;
import com.lawencon.e.learning.dto.scheduleexam.ScheduleExamDataDto;
import com.lawencon.e.learning.dto.scheduleexam.ScheduleExamDto;
import com.lawencon.e.learning.dto.scheduleexam.ScheduleExamsDto;
import com.lawencon.e.learning.model.ScheduleExam;
import com.lawencon.e.learning.service.ScheduleExamService;

@Service
public class ScheduleExamServiceImpl implements ScheduleExamService {

	private final ScheduleExamDao scheduleExamDao;

	public ScheduleExamServiceImpl(ScheduleExamDao scheduleExamDao) {
		this.scheduleExamDao = scheduleExamDao;
	}


	@Override
	public ScheduleExamsDto getAllByStudentSideForAttendance(Long studyClassId) {
		List<ScheduleExam> scheduleExams = new ArrayList<>();
		scheduleExams = scheduleExamDao.getAllByStudentSideForAttendance(studyClassId);
		final List<ScheduleExamDataDto>scheduleExamDataDtos = new ArrayList<>();
		for(int i = 0 ; i<scheduleExams.size(); i++) {
			final ScheduleExamDataDto scheduleExamDataDto = new ScheduleExamDataDto();
			scheduleExamDataDto.setId(scheduleExams.get(i).getId());
			scheduleExamDataDto.setStartExam(scheduleExams.get(i).getStartAt());
			scheduleExamDataDto.setFinishExam(scheduleExams.get(i).getFinishOn());
			scheduleExamDataDto.setExamName(scheduleExams.get(i).getExamId().getExamName());
			scheduleExamDataDto.setVer(scheduleExams.get(i).getVer());
			
			scheduleExamDataDtos.add(scheduleExamDataDto);
		}
		final ScheduleExamsDto scheduleExamsDto = new ScheduleExamsDto();
		scheduleExamsDto.setData(scheduleExamDataDtos);
		return scheduleExamsDto;
	}

	@Override
	public ScheduleExamsDto getAllByTeacherSide(Long classId) {
		List<ScheduleExam> scheduleExams = new ArrayList<>();
		scheduleExams = scheduleExamDao.getAllByTeacherSide(classId);
		final List<ScheduleExamDataDto>scheduleExamDataDtos = new ArrayList<>();
		for(int i = 0 ; i<scheduleExams.size(); i++) {
			final ScheduleExamDataDto scheduleExamDataDto = new ScheduleExamDataDto();
			scheduleExamDataDto.setId(scheduleExams.get(i).getId());
			scheduleExamDataDto.setStartExam(scheduleExams.get(i).getStartAt());
			scheduleExamDataDto.setFinishExam(scheduleExams.get(i).getFinishOn());
			scheduleExamDataDto.setExamName(scheduleExams.get(i).getExamId().getExamName());
			scheduleExamDataDto.setVer(scheduleExams.get(i).getVer());
			
			scheduleExamDataDtos.add(scheduleExamDataDto);
		}
		final ScheduleExamsDto scheduleExamsDto = new ScheduleExamsDto();
		scheduleExamsDto.setData(scheduleExamDataDtos);
		return scheduleExamsDto;	
	}

	@Override
	public ScheduleExamDto getById(Long id) {
		Optional<ScheduleExam> scheduleExamOptional = Optional.ofNullable(null);
		scheduleExamOptional = scheduleExamDao.getById(id);
		final ScheduleExamDataDto scheduleExamDataDto = new ScheduleExamDataDto();
		if(scheduleExamOptional.isPresent()) {
			scheduleExamDataDto.setId(scheduleExamOptional.get().getId());
			scheduleExamDataDto.setStartExam(scheduleExamOptional.get().getStartAt());
			scheduleExamDataDto.setFinishExam(scheduleExamOptional.get().getFinishOn());
			scheduleExamDataDto.setExamName(scheduleExamOptional.get().getExamId().getExamName());
			scheduleExamDataDto.setVer(scheduleExamOptional.get().getVer());
		}
		final ScheduleExamDto scheduleExamDto = new ScheduleExamDto();
		scheduleExamDto.setData(scheduleExamDataDto);
		return scheduleExamDto;
	}
}
