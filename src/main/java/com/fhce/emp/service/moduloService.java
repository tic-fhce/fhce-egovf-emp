package com.fhce.emp.service;

import java.util.List;

import com.fhce.emp.obj.moduloDtoResponse;

public interface moduloService {
	List<moduloDtoResponse>listar();
	List<moduloDtoResponse>getListarEmpleadoModulo(Long cif);
	
	List<moduloDtoResponse>getListarModuloCif(Long cif);
	

}
