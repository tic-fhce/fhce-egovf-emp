package com.fhce.emp.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.emp.dao.empleadoModuloDao;
import com.fhce.emp.model.empleadoModuloModel;
import com.fhce.emp.obj.empleadoModuloDtoRequest;
import com.fhce.emp.obj.empleadoModuloDtoResponse;
import com.fhce.emp.service.empleadoModuloService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class empleadoModuloServiceImpl implements empleadoModuloService{
	
	private final empleadoModuloDao empleadoModuloDao;
	private final ModelMapper modelMapper;
	
	@Transactional
	public empleadoModuloDtoResponse addHorario(empleadoModuloDtoRequest empleadoModuloDtoRequest) {
		empleadoModuloModel empleadoModuloModel = this.modelMapper.map(empleadoModuloDtoRequest, empleadoModuloModel.class);
		this.empleadoModuloDao.save(empleadoModuloModel);
		return this.modelMapper.map(empleadoModuloModel, empleadoModuloDtoResponse.class);
		
	}

}
