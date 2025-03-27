package com.fhce.emp.service;

import java.util.List;

import com.fhce.emp.obj.tipoEmpleadoDtoResponse;

public interface tipoEmpleadoService {
	List<tipoEmpleadoDtoResponse>listar();
	tipoEmpleadoDtoResponse getTipoEmpleado(Long id);

}
