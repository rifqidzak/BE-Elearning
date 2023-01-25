package com.lawencon.e.learning.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.e.learning.dao.ForumDao;
import com.lawencon.e.learning.model.Forum;

@Repository
public class ForumDaoImpl extends BaseDaoImpl implements ForumDao {

	@Override
	public List<Forum> getAllByTeacherSide(Long studyClassId) {
		final String sql = "SELECT f FROM Forum f" + " INNER JOIN FETCH f.studyClassDetailId scd "
				+ "	INNER JOIN FETCH scd.studyClassId sc" + " WHERE sc.id = :id ";
		List<Forum> forums = new ArrayList<>();
		forums = this.em.createQuery(sql, Forum.class).setParameter("id", studyClassId).getResultList();
		return forums;
	}

	@Override
	public List<Forum> getAllByStudentSide(Long studyClassDetailId) {
		final String sql = "SELECT f FROM Forum f" + " INNER JOIN FETCH f.studyClassDetailId scd "
				+ " WHERE scd.id = :id ";
		List<Forum> forums = new ArrayList<>();
		forums = this.em.createQuery(sql, Forum.class).setParameter("id", studyClassDetailId).getResultList();
		return forums;
	}

	@Override
	public Forum insert(Forum data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public Optional<Forum> getById(Long id) {
		Forum forum = new Forum();
		Optional<Forum> forumOptional = Optional.ofNullable(null);
		try {
			forum = this.em.find(Forum.class, id);
			this.em.detach(forum);
			forumOptional = Optional.ofNullable(forum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forumOptional;
	}

}
