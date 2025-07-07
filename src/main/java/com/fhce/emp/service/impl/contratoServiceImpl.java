package com.fhce.emp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.emp.dao.contratoDao;
import com.fhce.emp.dao.empleadoDao;
import com.fhce.emp.dao.empleadoModuloDao;
import com.fhce.emp.model.contratoModel;
import com.fhce.emp.model.empleadoModel;
import com.fhce.emp.model.empleadoModuloModel;
import com.fhce.emp.obj.contratoDtoRequest;
import com.fhce.emp.obj.contratoDtoResponse;
import com.fhce.emp.service.contratoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class contratoServiceImpl implements contratoService{
	
	private final contratoDao contratoDao;
	private final ModelMapper modelMapper;
	private final empleadoDao empleadoDao;
	private final empleadoModuloDao empleadoModuloDao;
	
	@Transactional
	public List<contratoDtoResponse> listar() {
		List<contratoDtoResponse> contratos = contratoDao.findAll().stream()
		        .map(contrato -> modelMapper.map(contrato, contratoDtoResponse.class))
		        .collect(Collectors.toList());
		return contratos;
	}
	 
	@Transactional
	public List<contratoDtoResponse>listarContratos(Long id){
		List<contratoDtoResponse>contratos = contratoDao.getContratos(id).stream()
				.map(contrato->modelMapper.map(contrato,contratoDtoResponse.class))
				.collect(Collectors.toList());
		return contratos;
	}
	
	@Transactional
	public contratoDtoResponse addContrato(contratoDtoRequest contratoDtoRequest) {
		//Creamos el empleado 
		// Buscamos en la Tabla Empleado si ya existe
		empleadoModel empleadoModel = this.empleadoDao.getEmpleado(contratoDtoRequest.getCif());
		
		if(empleadoModel == null) {
			//si no existe creamos uno con el cif
			empleadoModel = new empleadoModel();
			empleadoModel.setCif(contratoDtoRequest.getCif());
		}
		empleadoModel.setTipo_empleado_id(contratoDtoRequest.getIdTipoEmpleado());
		empleadoModel.setFecha(contratoDtoRequest.getInicio());
		empleadoModel.setEstado(1);
		empleadoModel.setSalida(contratoDtoRequest.getFin());
		this.empleadoDao.save(empleadoModel);
		//Terminamos de Crear el empleado
		
		
		//si es del tipo 2 cremos el modulo SCC directamente 
		if(contratoDtoRequest.getIdTipoEmpleado().longValue() == (long) 2){
			//Buscamos en la Tabla EmpleadoModulo si ya existe
			empleadoModuloModel empleadomoduloModel = this.empleadoModuloDao.getEmpleadoModulo(contratoDtoRequest.getCif());
			
			if(empleadomoduloModel == null) {
				//si no existe creamos uno con el cif
				empleadomoduloModel = new empleadoModuloModel();
				empleadomoduloModel.setCif(contratoDtoRequest.getCif());
			}
			empleadomoduloModel.setId_modulo((long) 1);
			empleadomoduloModel.setEstado(1);
			this.empleadoModuloDao.save(empleadomoduloModel);
		}
		//Terminamos de crear el Modulo SCC
		
		//Creamos el contrato del empleado
		contratoModel contratoModel = new contratoModel();
		contratoModel.setCif(contratoDtoRequest.getCif());
		contratoModel.setNumero_contrato(contratoDtoRequest.getNumero_contrato());
		contratoModel.setServicio(contratoDtoRequest.getServicio());
		contratoModel.setUnidad(contratoDtoRequest.getUnidad());
		contratoModel.setInicio(contratoDtoRequest.getInicio());
		contratoModel.setFin(contratoDtoRequest.getFin());
		contratoModel.setGestion(contratoDtoRequest.getGestion());
		contratoModel.setDetalle(contratoDtoRequest.getDetalle());
		contratoModel.setIdTipoEmpleado(contratoDtoRequest.getIdTipoEmpleado());
		contratoModel.setCargo(contratoDtoRequest.getCargo());
		this.contratoDao.save(contratoModel);
		//Terminamos de Crear el contrato del empleado
		
		return this.modelMapper.map(contratoModel, contratoDtoResponse.class);
	}
	
	@Transactional
	public contratoDtoResponse updateContrato(contratoDtoResponse contratoDtoResponse) {
		contratoModel contratoModel= new contratoModel();
		contratoModel.setId(contratoDtoResponse.getId());
		contratoModel.setCif(contratoDtoResponse.getCif());
		contratoModel.setNumero_contrato(contratoDtoResponse.getNumero_contrato());
		contratoModel.setServicio(contratoDtoResponse.getServicio());
		contratoModel.setUnidad(contratoDtoResponse.getUnidad());
		contratoModel.setInicio(contratoDtoResponse.getInicio());
		contratoModel.setFin(contratoDtoResponse.getFin());
		contratoModel.setGestion(contratoDtoResponse.getGestion());
		contratoModel.setDetalle(contratoDtoResponse.getDetalle());
		contratoModel.setIdTipoEmpleado(contratoDtoResponse.getIdTipoEmpleado());
		contratoModel.setCargo(contratoDtoResponse.getCargo());
				
		empleadoModel empleadoModel = this.empleadoDao.getEmpleado(contratoDtoResponse.getCif());
		empleadoModel.setFecha(contratoDtoResponse.getInicio());
		empleadoModel.setSalida(contratoDtoResponse.getFin());
		empleadoModel.setTipo_empleado_id(contratoDtoResponse.getIdTipoEmpleado());
		empleadoModel.setFoto(contratoDtoResponse.getFoto());
		
		this.empleadoDao.save(empleadoModel);
		
		this.contratoDao.save(contratoModel);
		return modelMapper.map(contratoModel, contratoDtoResponse.class);
	}

}
