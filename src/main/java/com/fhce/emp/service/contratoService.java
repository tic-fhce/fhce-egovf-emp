package com.fhce.emp.service;

import java.util.List;

import com.fhce.emp.obj.contratoDtoRequest;
import com.fhce.emp.obj.contratoDtoResponse;

public interface contratoService {
	List<contratoDtoResponse> listar();
	List<contratoDtoResponse>listarContratos(Long id);
	contratoDtoResponse addContrato(contratoDtoRequest contratoDtoRequest);
	contratoDtoResponse updateContrato(contratoDtoResponse contratoDtoResponse); 

}
