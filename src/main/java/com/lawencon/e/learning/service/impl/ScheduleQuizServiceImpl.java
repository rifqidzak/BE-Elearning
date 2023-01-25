package com.lawencon.e.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lawencon.e.learning.dao.ScheduleQuizDao;
import com.lawencon.e.learning.dto.schedulequiz.ScheduleQuizDataDto;
import com.lawencon.e.learning.dto.schedulequiz.ScheduleQuizDto;
import com.lawencon.e.learning.dto.schedulequiz.ScheduleQuizsDto;
import com.lawencon.e.learning.model.ScheduleQuiz;
import com.lawencon.e.learning.service.ScheduleQuizService;

@Service
public class ScheduleQuizServiceImpl implements ScheduleQuizService {

	private final ScheduleQuizDao scheduleQuizDao;

	public ScheduleQuizServiceImpl(ScheduleQuizDao scheduleQuizDao) {
		this.scheduleQuizDao = scheduleQuizDao;
	}


	@Override
	public ScheduleQuizsDto getAllByStudentSideForAttendance(Long studyClassId) {
		List<ScheduleQuiz> scheduleQuizs = new ArrayList<>();
		scheduleQuizs = scheduleQuizDao.getAllByStudentSideForAttendance(studyClassId);
		final List<ScheduleQuizDataDto>scheduleQuizDataDtos = new ArrayList<>();
		for(int i = 0 ; i<scheduleQuizs.size(); i++) {
			final ScheduleQuizDataDto scheduleQuizDataDto = new ScheduleQuizDataDto();
			scheduleQuizDataDto.setId(scheduleQuizs.get(i).getId());
			scheduleQuizDataDto.setStartQuiz(scheduleQuizs.get(i).getStartAt());
			scheduleQuizDataDto.setFinishQuiz(scheduleQuizs.get(i).getFinishOn());
			scheduleQuizDataDto.setQuizName(scheduleQuizs.get(i).getQuizId().getQuizName());
			scheduleQuizDataDto.setVer(scheduleQuizs.get(i).getVer());
			
			scheduleQuizDataDtos.add(scheduleQuizDataDto);
		}
		final ScheduleQuizsDto scheduleQuizsDto = new ScheduleQuizsDto();
		scheduleQuizsDto.setData(scheduleQuizDataDtos);
		return scheduleQuizsDto;
	}

	@Override
	public ScheduleQuizsDto getAllByTeacherSide(Long classId) {
		List<ScheduleQuiz> scheduleQuizs = new ArrayList<>();
		scheduleQuizs = scheduleQuizDao.getAllByTeacherSide(classId);
		final List<ScheduleQuizDataDto>scheduleQuizDataDtos = new ArrayList<>();
		for(int i = 0 ; i<scheduleQuizs.size(); i++) {
			final ScheduleQuizDataDto scheduleQuizDataDto = new ScheduleQuizDataDto();
			scheduleQuizDataDto.setId(scheduleQuizs.get(i).getId());
			scheduleQuizDataDto.setStartQuiz(scheduleQuizs.get(i).getStartAt());
			scheduleQuizDataDto.setFinishQuiz(scheduleQuizs.get(i).getFinishOn());
			scheduleQuizDataDto.setQuizName(scheduleQuizs.get(i).getQuizId().getQuizName());
			scheduleQuizDataDto.setVer(scheduleQuizs.get(i).getVer());
			
			scheduleQuizDataDtos.add(scheduleQuizDataDto);
		}
		final ScheduleQuizsDto scheduleQuizsDto = new ScheduleQuizsDto();
		scheduleQuizsDto.setData(scheduleQuizDataDtos);
		return scheduleQuizsDto;	
	}

	@Override
	public ScheduleQuizDto getById(Long id) {
		Optional<ScheduleQuiz> scheduleQuizOptional = Optional.ofNullable(null);
		scheduleQuizOptional = scheduleQuizDao.getById(id);
		final ScheduleQuizDataDto scheduleQuizDataDto = new ScheduleQuizDataDto();
		if(scheduleQuizOptional.isPresent()) {
			scheduleQuizDataDto.setId(scheduleQuizOptional.get().getId());
			scheduleQuizDataDto.setStartQuiz(scheduleQuizOptional.get().getStartAt());
			scheduleQuizDataDto.setFinishQuiz(scheduleQuizOptional.get().getFinishOn());
			scheduleQuizDataDto.setQuizName(scheduleQuizOptional.get().getQuizId().getQuizName());
			scheduleQuizDataDto.setVer(scheduleQuizOptional.get().getVer());
		}
		final ScheduleQuizDto scheduleQuizDto = new ScheduleQuizDto();
		scheduleQuizDto.setData(scheduleQuizDataDto);
		return scheduleQuizDto;
	}

}
