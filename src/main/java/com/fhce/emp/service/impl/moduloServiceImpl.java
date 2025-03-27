package com.fhce.emp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.emp.dao.empleadoModuloDao;
import com.fhce.emp.dao.moduloDao;
import com.fhce.emp.model.moduloModel;
import com.fhce.emp.obj.empleadoModuloDtoResponse;
import com.fhce.emp.obj.moduloDtoResponse;
import com.fhce.emp.service.moduloService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class moduloServiceImpl implements moduloService{
	private final moduloDao moduloDao;
	private final ModelMapper modelMapper;
	private final empleadoModuloDao empleadoModuloDao;
	
	@Transactional
	public List<moduloDtoResponse>listar(){
		List<moduloDtoResponse> modulos = moduloDao.findAll().stream()
		        .map(modulo -> modelMapper.map(modulo, moduloDtoResponse.class))
		        .collect(Collectors.toList());
		return modulos;
	}
	@Transactional
	public List<moduloDtoResponse>getListarEmpleadoModulo(Long cif){
				
		List<empleadoModuloDtoResponse> listaEmpleadoModulo = this.empleadoModuloDao.getCif(cif).stream()
		        .map(empleadoModulo -> this.modelMapper.map(empleadoModulo, empleadoModuloDtoResponse.class))
		        .collect(Collectors.toList());
		
		List<Long>modulo = new ArrayList<Long>();
		
		List<moduloDtoResponse> auxModulo = moduloDao.findAll().stream()
		        .map(moduloAux -> modelMapper.map(moduloAux, moduloDtoResponse.class))
		        .collect(Collectors.toList());
		
		List<moduloDtoResponse>lista = new ArrayList<moduloDtoResponse>();
		
		int indice=0;
		for(int i=0;i<listaEmpleadoModulo.size();i++)
			modulo.add(listaEmpleadoModulo.get(i).getId_modulo());
		
		for(int i=0;i<auxModulo.size();i++) {
			indice=modulo.indexOf(auxModulo.get(i).getId());
			if(indice==-1) {
				lista.add(auxModulo.get(i));
			}
		}
		return(lista);
	}
	@Transactional
	
	public List<moduloDtoResponse>getListarModuloCif(Long cif){
		
		List<empleadoModuloDtoResponse> listaEmpleadoModulo = this.empleadoModuloDao.getListaModuloEmpleado(cif).stream()
		        .map(modulo -> modelMapper.map(modulo, empleadoModuloDtoResponse.class))
		        .collect(Collectors.toList());
		
		List<moduloModel>lista=new ArrayList<moduloModel>();
		for(int i=0;i<listaEmpleadoModulo.size();i++) {
			lista.add(this.moduloDao.getModulo(listaEmpleadoModulo.get(i).getId_modulo()));
		}
		return(lista.stream().map(modulo ->modelMapper.map(modulo, moduloDtoResponse.class)).collect(Collectors.toList()));
	}

}
