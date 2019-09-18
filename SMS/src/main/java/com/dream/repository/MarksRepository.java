package com.dream.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dream.model.Marks;

public interface MarksRepository extends CrudRepository<Marks, Integer>{

	@Query(value = "select m.* from marks m where m.st_id=:id", nativeQuery = true)
	Marks getMarksByStudent(@Param("id") int id);
	
}
