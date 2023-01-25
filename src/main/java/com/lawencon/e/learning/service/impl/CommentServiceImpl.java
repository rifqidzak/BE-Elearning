package com.lawencon.e.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.lawencon.e.learning.constant.MessageEnum;
import com.lawencon.e.learning.dao.CommentDao;
import com.lawencon.e.learning.dao.ForumDao;
import com.lawencon.e.learning.dao.UserDao;
import com.lawencon.e.learning.dto.comment.CommentDataDto;
import com.lawencon.e.learning.dto.comment.CommentInsertReqDataDto;
import com.lawencon.e.learning.dto.comment.CommentInsertResDataDto;
import com.lawencon.e.learning.dto.comment.CommentInsertResDto;
import com.lawencon.e.learning.dto.comment.CommentsDto;
import com.lawencon.e.learning.model.Comment;
import com.lawencon.e.learning.model.Forum;
import com.lawencon.e.learning.service.CommentService;
import com.lawencon.e.learning.service.PrincipalService;

@Service
public class CommentServiceImpl implements CommentService {

	private final CommentDao commentDao;
	private final ForumDao forumDao;
	private final UserDao userDao;
	private final PrincipalService principalService;
	public CommentServiceImpl(SessionFactory sessionFactory, CommentDao commentDao, ForumDao forumDao,
			UserDao userDao, PrincipalService principalService) {
		this.commentDao = commentDao;
		this.forumDao = forumDao;
		this.userDao = userDao;
		this.principalService = principalService;
	}

	@Override
	public CommentsDto getAll(Long forumId) {
		final List<Comment> comments = commentDao.getAll(forumId);
		final List<CommentDataDto> commentDataDtos = new ArrayList<>();

		for (int i = 0; i < comments.size(); i++) {
			final CommentDataDto commentDataDto = new CommentDataDto();
			commentDataDto.setBodyComment(comments.get(i).getBodyComment());
			commentDataDto.setName(userDao.getById(comments.get(i).getCreatedBy()).get().getFullNameUser());
			commentDataDto.setId(comments.get(i).getId());
			commentDataDto.setVer(comments.get(i).getVer());
			
			commentDataDtos.add(commentDataDto);
		}

		final CommentsDto commentsDto = new CommentsDto();
		commentsDto.setData(commentDataDtos);
		return commentsDto;
	}

	@Transactional
	@Override
	public CommentInsertResDto insert(CommentInsertReqDataDto data) {
		Comment commentInsert = new Comment();
		Optional<Forum> forumOptional = forumDao.getById(data.getForumId());
		commentInsert.setForumId(forumOptional.get());
		commentInsert.setBodyComment(data.getBodyComment());
		commentInsert.setCreatedBy(principalService.getPrincipal().getId());
		commentInsert = commentDao.insert(commentInsert);

		CommentInsertResDataDto commentInsertResDataDto = new CommentInsertResDataDto();
		commentInsertResDataDto.setId(commentInsert.getId());

		CommentInsertResDto commentInsertResDto = new CommentInsertResDto();
		commentInsertResDto.setData(commentInsertResDataDto);
		commentInsertResDto.setMessage(MessageEnum.INSERTED.toString());
		return commentInsertResDto;
	}

}
