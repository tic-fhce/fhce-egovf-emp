package com.fhce.emp.service;

import java.util.List;

import com.fhce.emp.obj.empleadoDtoRequest;
import com.fhce.emp.obj.empleadoDtoResponse;
import com.fhce.emp.obj.empleadoObj;

public interface empleadoService {
	List<empleadoDtoResponse> listar();
	empleadoDtoResponse addEmpleado(empleadoDtoRequest empleadoDtoRequest);
	empleadoDtoResponse updateEmpleado(empleadoDtoResponse empleadoDtoResponse); 
	List<empleadoObj> getListaEmpleado(Long tipo);
	empleadoObj getEmpleado(Long cif);
}
