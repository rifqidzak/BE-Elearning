package com.lawencon.e.learning.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseDaoImpl {
public EntityManager em;
	
	@PersistenceContext
	public void setEm(final EntityManager em) {
		this.em = em;
	}
}
