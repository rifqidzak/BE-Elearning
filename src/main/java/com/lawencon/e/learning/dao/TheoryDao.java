package com.lawencon.e.learning.dao;

import java.util.Optional;

import com.lawencon.e.learning.model.Theory;

public interface TheoryDao {
	Theory insert(Theory data);

	boolean deleteById(Long id);

	Optional<Theory> getById(Long id);

}
