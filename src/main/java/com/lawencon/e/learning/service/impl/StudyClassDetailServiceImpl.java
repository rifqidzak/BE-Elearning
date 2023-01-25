package com.lawencon.e.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.lawencon.e.learning.constant.MessageEnum;
import com.lawencon.e.learning.dao.StudyClassDao;
import com.lawencon.e.learning.dao.StudyClassDetailDao;
import com.lawencon.e.learning.dao.UserDao;
import com.lawencon.e.learning.dto.studyclassdetail.StudyClassDetailDataDto;
import com.lawencon.e.learning.dto.studyclassdetail.StudyClassDetailDto;
import com.lawencon.e.learning.dto.studyclassdetail.StudyClassDetailInsertReqDataDto;
import com.lawencon.e.learning.dto.studyclassdetail.StudyClassDetailInsertResDataDto;
import com.lawencon.e.learning.dto.studyclassdetail.StudyClassDetailInsertResDto;
import com.lawencon.e.learning.dto.studyclassdetail.StudyClassesDetailDto;
import com.lawencon.e.learning.model.StudyClass;
import com.lawencon.e.learning.model.StudyClassDetail;
import com.lawencon.e.learning.model.User;
import com.lawencon.e.learning.service.PrincipalService;
import com.lawencon.e.learning.service.StudyClassDetailService;

@Service
public class StudyClassDetailServiceImpl implements StudyClassDetailService {
	private final StudyClassDetailDao studyClassDetailDao;
	private final UserDao userDao;
	private final StudyClassDao studyClassDao;
	private final PrincipalService principalService;
	public StudyClassDetailServiceImpl(SessionFactory sessionFactory, StudyClassDetailDao studyClassDetailDao,
			UserDao userDao, StudyClassDao studyClassDao, PrincipalService principalService) {
		this.studyClassDetailDao = studyClassDetailDao;
		this.studyClassDao = studyClassDao;
		this.userDao = userDao;
		this.principalService = principalService;
	}

	@Transactional
	@Override
	public StudyClassDetailInsertResDto insert(StudyClassDetailInsertReqDataDto data) {
		StudyClassDetail studyClassDetailInsert = new StudyClassDetail();
		final Optional<StudyClass> studyClassOptional = studyClassDao.getById(data.getStudyClassId());
		final Optional<User> userOptional = userDao.getById(principalService.getPrincipal().getId());

		studyClassDetailInsert.setStudentId(userOptional.get());
		studyClassDetailInsert.setStudyClassId(studyClassOptional.get());
		studyClassDetailInsert.setCreatedBy(principalService.getPrincipal().getId());
		studyClassDetailInsert = studyClassDetailDao.insert(studyClassDetailInsert);
		
		StudyClassDetailInsertResDataDto studyClassDetailInsertResDataDto = new StudyClassDetailInsertResDataDto();
		studyClassDetailInsertResDataDto.setId(studyClassDetailInsert.getId());

		StudyClassDetailInsertResDto studyClassDetailInsertResDto = new StudyClassDetailInsertResDto();
		studyClassDetailInsertResDto.setData(studyClassDetailInsertResDataDto);
		studyClassDetailInsertResDto.setMessage(MessageEnum.INSERTED.toString());
		return studyClassDetailInsertResDto;
	}

	@Override
	public StudyClassesDetailDto getAllByStudentId(Long id) {
		final List<StudyClassDetail> studyClassDetails = studyClassDetailDao.getAllByStudentId(id);
		final List<StudyClassDetailDataDto> studyClassDetailDataDtos = new ArrayList<>();

		for (int i = 0; i < studyClassDetails.size(); i++) {
			final StudyClassDetailDataDto studyClassDetailDataDto = new StudyClassDetailDataDto();
			studyClassDetailDataDto.setId(studyClassDetails.get(i).getId());
			studyClassDetailDataDto.setClassCode(studyClassDetails.get(i).getStudyClassId().getStudyClassCode());
			studyClassDetailDataDto.setClassName(studyClassDetails.get(i).getStudyClassId().getStudyClassName());
			studyClassDetailDataDto.setPhotoClassId(studyClassDetails.get(i).getStudyClassId().getPhotoClass().getId());

			studyClassDetailDataDto
					.setTeacherName(studyClassDetails.get(i).getStudyClassId().getTeacherId().getFullNameUser());
			studyClassDetailDataDto.setId(studyClassDetails.get(i).getId());
			studyClassDetailDataDto.setVer(studyClassDetails.get(i).getVer());
			studyClassDetailDataDto.setStudyClassId(studyClassDetails.get(i).getStudyClassId().getId());
			studyClassDetailDataDto.setDescription(studyClassDetails.get(i).getStudyClassId().getStudyClassDescription());
			studyClassDetailDataDtos.add(studyClassDetailDataDto);
		}

		StudyClassesDetailDto studyClassesDetailDto = new StudyClassesDetailDto();
		studyClassesDetailDto.setData(studyClassDetailDataDtos);
		return studyClassesDetailDto;
	}

	@Override
	public StudyClassDetailDto getById(Long id) {
		final Optional<StudyClassDetail> studyClassDetailOptional = studyClassDetailDao.getById(id);
		final StudyClassDetailDataDto studyClassDetailDataDto = new StudyClassDetailDataDto();

		if (studyClassDetailOptional.isPresent()) {
			studyClassDetailDataDto.setId(studyClassDetailOptional.get().getId());
			studyClassDetailDataDto.setClassCode(studyClassDetailOptional.get().getStudyClassId().getStudyClassCode());
			studyClassDetailDataDto.setClassName(studyClassDetailOptional.get().getStudyClassId().getStudyClassName());
			studyClassDetailDataDto
					.setPhotoClassId(studyClassDetailOptional.get().getStudyClassId().getPhotoClass().getId());

			studyClassDetailDataDto
					.setTeacherName(studyClassDetailOptional.get().getStudyClassId().getTeacherId().getFullNameUser());
			studyClassDetailDataDto.setId(studyClassDetailOptional.get().getId());
			studyClassDetailDataDto.setVer(studyClassDetailOptional.get().getVer());
			studyClassDetailDataDto.setStudyClassId(studyClassDetailOptional.get().getStudyClassId().getId());
			studyClassDetailDataDto.setDescription(studyClassDetailOptional.get().getStudyClassId().getStudyClassDescription());
		}
		StudyClassDetailDto studyClassDetailDto = new StudyClassDetailDto();
		studyClassDetailDto.setData(studyClassDetailDataDto);
		return studyClassDetailDto;
	}

}
