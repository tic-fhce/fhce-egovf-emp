package com.fhce.emp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.emp.dao.tipoEmpleadoDao;
import com.fhce.emp.obj.tipoEmpleadoDtoResponse;
import com.fhce.emp.service.tipoEmpleadoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class tipoEmpleadoServiceImpl implements tipoEmpleadoService{
	
	private final tipoEmpleadoDao tipoEmpleadoDao;
	private final ModelMapper modelMapper;
	
	@Transactional
	public List<tipoEmpleadoDtoResponse>listar(){
		List<tipoEmpleadoDtoResponse> tipoEmpleados = this.tipoEmpleadoDao.findAll().stream()
		        .map(tipoEmpleado -> modelMapper.map(tipoEmpleado, tipoEmpleadoDtoResponse.class))
		        .collect(Collectors.toList());
		return tipoEmpleados;
	}
	
	@Transactional
	public tipoEmpleadoDtoResponse getTipoEmpleado(Long id){
		tipoEmpleadoDtoResponse tipoEmpleadoDtoResponse = modelMapper.map(this.tipoEmpleadoDao.getTipoempleado(id), tipoEmpleadoDtoResponse.class);
		return (tipoEmpleadoDtoResponse);
	}
	

}
