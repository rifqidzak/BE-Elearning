package com.lawencon.e.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.lawencon.e.learning.constant.MessageEnum;
import com.lawencon.e.learning.dao.ForumDao;
import com.lawencon.e.learning.dao.StudyClassDetailDao;
import com.lawencon.e.learning.dao.UserDao;
import com.lawencon.e.learning.dto.forum.ForumDataDto;
import com.lawencon.e.learning.dto.forum.ForumDto;
import com.lawencon.e.learning.dto.forum.ForumInsertReqDataDto;
import com.lawencon.e.learning.dto.forum.ForumInsertResDataDto;
import com.lawencon.e.learning.dto.forum.ForumInsertResDto;
import com.lawencon.e.learning.dto.forum.ForumsDto;
import com.lawencon.e.learning.model.Forum;
import com.lawencon.e.learning.model.StudyClassDetail;
import com.lawencon.e.learning.model.User;
import com.lawencon.e.learning.service.ForumService;
import com.lawencon.e.learning.service.PrincipalService;

@Service
public class ForumServiceImpl implements ForumService {
	private final ForumDao forumDao;
	private final StudyClassDetailDao studyClassDetailDao;
	private final UserDao userDao;
	private final PrincipalService principalService;

	public ForumServiceImpl(SessionFactory sessionFactory, ForumDao forumDao, StudyClassDetailDao studyClassDetailDao,
			UserDao userDao, PrincipalService principalService) {
		this.forumDao = forumDao;
		this.studyClassDetailDao = studyClassDetailDao;
		this.userDao = userDao;
		this.principalService =  principalService;
	}

	@Override
	public ForumsDto getAllByTeacherSide(Long studyClassId) {
		final List<Forum> forums = forumDao.getAllByTeacherSide(studyClassId);
		final List<ForumDataDto> forumDataDtos = new ArrayList<>();

		for (int i = 0; i < forums.size(); i++) {
			final ForumDataDto forumDataDto = new ForumDataDto();
			forumDataDto.setTitleForum(forums.get(i).getTitleForum());
			forumDataDto.setBodyForum(forums.get(i).getBodyForum());
			forumDataDto.setStudentName(forums.get(i).getStudentId().getFullNameUser());
			forumDataDto.setClassName(forums.get(i).getStudyClassDetailId().getStudyClassId().getStudyClassName());
			forumDataDto.setId(forums.get(i).getId());
			forumDataDto.setVer(forums.get(i).getVer());
			forumDataDto.setCreatedAt(forums.get(i).getCreatedAt());
			
			forumDataDtos.add(forumDataDto);
		}

		final ForumsDto forumsDto = new ForumsDto();
		forumsDto.setData(forumDataDtos);
		return forumsDto;
	}

	@Override
	public ForumsDto getAllByStudentSide(Long studyClassDetailId) {
		final List<Forum> forums = forumDao.getAllByStudentSide(studyClassDetailId);
		final List<ForumDataDto> forumDataDtos = new ArrayList<>();

		for (int i = 0; i < forums.size(); i++) {
			final ForumDataDto forumDataDto = new ForumDataDto();
			forumDataDto.setTitleForum(forums.get(i).getTitleForum());
			forumDataDto.setBodyForum(forums.get(i).getBodyForum());
			forumDataDto.setStudentName(forums.get(i).getStudentId().getFullNameUser());
			forumDataDto.setClassName(forums.get(i).getStudyClassDetailId().getStudyClassId().getStudyClassName());
			forumDataDto.setId(forums.get(i).getId());
			forumDataDto.setVer(forums.get(i).getVer());
			forumDataDto.setCreatedAt(forums.get(i).getCreatedAt());
			forumDataDtos.add(forumDataDto);
		}

		final ForumsDto forumsDto = new ForumsDto();
		forumsDto.setData(forumDataDtos);
		return forumsDto;
	}

	@Override
	public ForumDto getById(Long id) {
		final Optional<Forum> forumOptional = forumDao.getById(id);
		final ForumDataDto forumDataDto = new ForumDataDto();
		if (forumOptional.isPresent()) {
			forumDataDto.setTitleForum(forumOptional.get().getTitleForum());
			forumDataDto.setBodyForum(forumOptional.get().getBodyForum());
			forumDataDto.setStudentName(forumOptional.get().getStudentId().getFullNameUser());
			forumDataDto
					.setClassName(forumOptional.get().getStudyClassDetailId().getStudyClassId().getStudyClassName());
			forumDataDto.setId(forumOptional.get().getId());
			forumDataDto.setCreatedAt(forumOptional.get().getCreatedAt());
			forumDataDto.setVer(forumOptional.get().getVer());
		}

		final ForumDto forumDto = new ForumDto();
		forumDto.setData(forumDataDto);
		return forumDto;
	}

	@Transactional
	@Override
	public ForumInsertResDto insert(ForumInsertReqDataDto data) {
		Forum forumInsert = new Forum();
		forumInsert.setTitleForum(data.getTitleForum());
		forumInsert.setBodyForum(data.getBodyForum());

		final Optional<User> userOptional = userDao.getById(principalService.getPrincipal().getId());
		final Optional<StudyClassDetail> studyClassDetailOptional = studyClassDetailDao
				.getById(data.getStudyClassDetailId());

		forumInsert.setStudentId(userOptional.get());
		forumInsert.setStudyClassDetailId(studyClassDetailOptional.get());
		forumInsert.setCreatedBy(principalService.getPrincipal().getId());

		forumInsert = forumDao.insert(forumInsert);

		final ForumInsertResDataDto forumInsertResDataDto = new ForumInsertResDataDto();
		forumInsertResDataDto.setId(forumInsert.getId());

		final ForumInsertResDto forumInsertResDto = new ForumInsertResDto();
		forumInsertResDto.setData(forumInsertResDataDto);
		forumInsertResDto.setMessage(MessageEnum.INSERTED.toString());

		return forumInsertResDto;
	}

}
