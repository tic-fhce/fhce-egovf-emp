package com.fhce.emp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.emp.model.contratoModel;

public interface contratoDao extends JpaRepository<contratoModel, Long>{
	@Query(value = "select * from contrato where _01cif=? order by id desc",nativeQuery=true)
	List<contratoModel>getContratos(Long cif);
}
