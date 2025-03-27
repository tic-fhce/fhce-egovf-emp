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
		
		empleadoModel empleadoModel = this.empleadoDao.getEmpleado(contratoDtoRequest.getCif());
		
		if(empleadoModel == null) {
			empleadoModel = new empleadoModel();
			empleadoModel.setCif(contratoDtoRequest.getCif());
		}
		empleadoModel.setTipo_empleado_id(contratoDtoRequest.getIdTipoEmpleado());
		empleadoModel.setFecha(contratoDtoRequest.getInicio());
		empleadoModel.setEstado(1);
		empleadoModel.setSalida(contratoDtoRequest.getFin());
		this.empleadoDao.save(empleadoModel);
		
		if(contratoDtoRequest.getIdTipoEmpleado().longValue() == (long) 2 || contratoDtoRequest.getIdTipoEmpleado().longValue() == (long) 4){
			empleadoModuloModel empleadomoduloModel = this.empleadoModuloDao.getEmpleadoModulo(contratoDtoRequest.getCif());
			if(empleadomoduloModel == null) {
				empleadomoduloModel = new empleadoModuloModel();
				empleadomoduloModel.setCif(contratoDtoRequest.getCif());
			}
			empleadomoduloModel.setId_modulo((long) 1);
			empleadomoduloModel.setEstado(1);
			this.empleadoModuloDao.save(empleadomoduloModel);
		}
		
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
		this.contratoDao.save(contratoModel);
		
		return this.modelMapper.map(contratoModel, contratoDtoResponse.class);
	}
	
	@Transactional
	public contratoDtoResponse updateContrato(contratoDtoResponse contratoDtoResponse) {
		contratoModel contratoModel= new contratoModel();
		contratoModel.setId(contratoDtoResponse.getId());
		contratoModel.setCif(contratoDtoResponse.getCif());
		contratoModel.setDetalle(contratoDtoResponse.getDetalle());
		contratoModel.setFin(contratoDtoResponse.getFin());
		contratoModel.setGestion(contratoDtoResponse.getGestion());
		contratoModel.setIdTipoEmpleado(contratoDtoResponse.getIdTipoEmpleado());
		contratoModel.setInicio(contratoDtoResponse.getInicio());
		contratoModel.setNumero_contrato(contratoDtoResponse.getNumero_contrato());
		contratoModel.setServicio(contratoDtoResponse.getServicio());
		contratoModel.setUnidad(contratoDtoResponse.getUnidad());
		
		empleadoModel empleadoModel = this.empleadoDao.getEmpleado(contratoDtoResponse.getCif());
		empleadoModel.setFecha(contratoDtoResponse.getInicio());
		empleadoModel.setSalida(contratoDtoResponse.getFin());
		this.empleadoDao.save(empleadoModel);
		
		this.contratoDao.save(contratoModel);
		return modelMapper.map(contratoModel, contratoDtoResponse.class);
	}

}
