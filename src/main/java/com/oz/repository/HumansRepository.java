package com.oz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oz.model.Human;

@Repository
public interface HumansRepository extends CrudRepository<Human, Long>{

	@Query(value = "SELECT * FROM humans WHERE id = ?", nativeQuery = true)
	public Human getHuman(Long id);
	
	@Query(value = "SELECT * FROM humans order by id asc", nativeQuery = true)
	public List<Human> getHumans();
}
