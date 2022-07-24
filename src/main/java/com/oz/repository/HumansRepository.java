package com.oz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oz.model.human.HumanModelDAO;

@Repository
public interface HumansRepository extends CrudRepository<HumanModelDAO, Long>{

	@Query(value = "SELECT * FROM humans WHERE id = ?", nativeQuery = true)
	public HumanModelDAO getHuman(Long id);
	
	@Query(value = "SELECT * FROM humans order by id asc", nativeQuery = true)
	public List<HumanModelDAO> getHumans();
}
