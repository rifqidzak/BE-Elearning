package com.lawencon.e.learning.dao;

import java.util.List;
import java.util.Optional;

public interface BaseMasterDao<T> {
	T insert(T data);

	T update(T data);

	List<T> getAll();

	Optional<T> getById(Long id);

	boolean deleteById(final Long id);
}
