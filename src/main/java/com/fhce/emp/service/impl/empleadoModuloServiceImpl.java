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
	
	@Transactional
	public empleadoModuloDtoResponse addEmpleadoModulo(empleadoModuloDtoRequest empleadoModuloDtoRequest){
		
		//buscamos el modulo del empleado 
		empleadoModuloModel empleadoModuloModel = this.empleadoModuloDao.getEmpleadoModulo(empleadoModuloDtoRequest.getCif());
		
		if(empleadoModuloModel == null) {
			//si no existe creamos uno con el cif
			empleadoModuloModel = new empleadoModuloModel();
			empleadoModuloModel.setCif(empleadoModuloDtoRequest.getCif());
		}
		empleadoModuloModel.setId_modulo(empleadoModuloDtoRequest.getId_modulo());
		empleadoModuloModel.setEstado(empleadoModuloDtoRequest.getEstado());
		
		//Guardamos el modulo del empleado
		this.empleadoModuloDao.save(empleadoModuloModel);
		
		return this.modelMapper.map(empleadoModuloModel, empleadoModuloDtoResponse.class);
		
	}

}
