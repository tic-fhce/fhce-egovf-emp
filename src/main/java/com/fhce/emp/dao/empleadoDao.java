package com.fhce.emp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.emp.model.empleadoModel;

public interface empleadoDao extends JpaRepository<empleadoModel, Long>{
	
	@Query(value = "select * from empleado where _01cif=?",nativeQuery=true)
	empleadoModel getEmpleado(Long cif);
}
