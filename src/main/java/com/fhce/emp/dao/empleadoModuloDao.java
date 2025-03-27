package com.fhce.emp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.emp.model.empleadoModuloModel;

public interface empleadoModuloDao extends JpaRepository<empleadoModuloModel, Long>{
	@Query(value = "select * from empleadomodulo where _01cif=?",nativeQuery=true)
	List<empleadoModuloModel>getCif(Long cif);
	
	@Query(value = "select * from empleadomodulo where _01cif=? and _03estado = 1",nativeQuery=true)
	List<empleadoModuloModel>getListaModuloEmpleado(Long cif);
	
	@Query(value = "select * from empleadomodulo where _01cif=?",nativeQuery=true)
	empleadoModuloModel getEmpleadoModulo(Long cif);

}