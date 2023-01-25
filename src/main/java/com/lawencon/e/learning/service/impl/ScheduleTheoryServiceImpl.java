package com.lawencon.e.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lawencon.e.learning.dao.ScheduleTheoryDao;
import com.lawencon.e.learning.dto.scheduletheory.ScheduleTheoriesDto;
import com.lawencon.e.learning.dto.scheduletheory.ScheduleTheoryDataDto;
import com.lawencon.e.learning.dto.scheduletheory.ScheduleTheoryDto;
import com.lawencon.e.learning.model.ScheduleTheory;
import com.lawencon.e.learning.service.ScheduleTheoryService;

@Service
public class ScheduleTheoryServiceImpl implements ScheduleTheoryService {

	private final ScheduleTheoryDao scheduleTheoryDao;

	public ScheduleTheoryServiceImpl(ScheduleTheoryDao scheduleTheoryDao) {
		this.scheduleTheoryDao = scheduleTheoryDao;
	}


	@Override
	public ScheduleTheoriesDto getAllByStudentSideForAttendance(Long studyClassId) {
		List<ScheduleTheory> scheduleTheorys = new ArrayList<>();
		scheduleTheorys = scheduleTheoryDao.getAllByStudentSideForAttendance(studyClassId);
		final List<ScheduleTheoryDataDto>scheduleTheoryDataDtos = new ArrayList<>();
		for(int i = 0 ; i<scheduleTheorys.size(); i++) {
			final ScheduleTheoryDataDto scheduleTheoryDataDto = new ScheduleTheoryDataDto();
			scheduleTheoryDataDto.setId(scheduleTheorys.get(i).getId());
			scheduleTheoryDataDto.setStartTheory(scheduleTheorys.get(i).getStartAt());
			scheduleTheoryDataDto.setFinishTheory(scheduleTheorys.get(i).getFinishOn());
			scheduleTheoryDataDto.setTheoryName(scheduleTheorys.get(i).getTheoryId().getTheoryName());
			scheduleTheoryDataDto.setVer(scheduleTheorys.get(i).getVer());
			
			scheduleTheoryDataDtos.add(scheduleTheoryDataDto);
		}
		final ScheduleTheoriesDto scheduleTheorysDto = new ScheduleTheoriesDto();
		scheduleTheorysDto.setData(scheduleTheoryDataDtos);
		return scheduleTheorysDto;
	}

	@Override
	public ScheduleTheoriesDto getAllByTeacherSide(Long classId) {
		List<ScheduleTheory> scheduleTheorys = new ArrayList<>();
		scheduleTheorys = scheduleTheoryDao.getAllByTeacherSide(classId);
		final List<ScheduleTheoryDataDto>scheduleTheoryDataDtos = new ArrayList<>();
		for(int i = 0 ; i<scheduleTheorys.size(); i++) {
			final ScheduleTheoryDataDto scheduleTheoryDataDto = new ScheduleTheoryDataDto();
			scheduleTheoryDataDto.setId(scheduleTheorys.get(i).getId());
			scheduleTheoryDataDto.setStartTheory(scheduleTheorys.get(i).getStartAt());
			scheduleTheoryDataDto.setFinishTheory(scheduleTheorys.get(i).getFinishOn());
			scheduleTheoryDataDto.setTheoryName(scheduleTheorys.get(i).getTheoryId().getTheoryName());
			scheduleTheoryDataDto.setVer(scheduleTheorys.get(i).getVer());
			
			scheduleTheoryDataDtos.add(scheduleTheoryDataDto);
		}
		final ScheduleTheoriesDto scheduleTheorysDto = new ScheduleTheoriesDto();
		scheduleTheorysDto.setData(scheduleTheoryDataDtos);
		return scheduleTheorysDto;
	}

	@Override
	public ScheduleTheoryDto getById(Long id) {
		Optional<ScheduleTheory> scheduleTheoryOptional = Optional.ofNullable(null);
		scheduleTheoryOptional = scheduleTheoryDao.getById(id);
		final ScheduleTheoryDataDto scheduleTheoryDataDto = new ScheduleTheoryDataDto();
		if(scheduleTheoryOptional.isPresent()) {
			scheduleTheoryDataDto.setId(scheduleTheoryOptional.get().getId());
			scheduleTheoryDataDto.setStartTheory(scheduleTheoryOptional.get().getStartAt());
			scheduleTheoryDataDto.setFinishTheory(scheduleTheoryOptional.get().getFinishOn());
			scheduleTheoryDataDto.setTheoryName(scheduleTheoryOptional.get().getTheoryId().getTheoryName());
			scheduleTheoryDataDto.setVer(scheduleTheoryOptional.get().getVer());
		}
		final ScheduleTheoryDto scheduleTheoryDto = new ScheduleTheoryDto();
		scheduleTheoryDto.setData(scheduleTheoryDataDto);
		return scheduleTheoryDto;
	}

}
