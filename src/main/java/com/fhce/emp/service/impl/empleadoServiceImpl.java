package com.fhce.emp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.emp.dao.contratoDao;
import com.fhce.emp.dao.empleadoDao;
import com.fhce.emp.dao.empleadoModuloDao;
import com.fhce.emp.dao.tipoEmpleadoDao;
import com.fhce.emp.model.empleadoModel;
import com.fhce.emp.model.empleadoModuloModel;
import com.fhce.emp.obj.contratoDtoResponse;
import com.fhce.emp.obj.empleadoDtoRequest;
import com.fhce.emp.obj.empleadoDtoResponse;
import com.fhce.emp.obj.empleadoObj;
import com.fhce.emp.service.empleadoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class empleadoServiceImpl implements empleadoService{
	
	private final empleadoDao empleadoDao;
	private final ModelMapper modelMapper;
	private final empleadoModuloDao empleadoModuloDao;
	private final tipoEmpleadoDao tipoempleadoDao;
	private final contratoDao contratoDao;
	
	@Transactional
	public List<empleadoDtoResponse> listar() {
		List<empleadoDtoResponse> empleados = this.empleadoDao.findAll().stream()
		        .map(empleado -> this.modelMapper.map(empleado, empleadoDtoResponse.class))
		        .collect(Collectors.toList());
		return empleados;
	}
	
	@Transactional
	public empleadoDtoResponse addEmpleado(empleadoDtoRequest empleadoDtoRequest) {
		empleadoModel empleadoModel = this.modelMapper.map(empleadoDtoRequest, empleadoModel.class);
		this.empleadoDao.save(empleadoModel);
		return this.modelMapper.map(empleadoModel, empleadoDtoResponse.class);
		
	}
	@Transactional
	public empleadoDtoResponse updateEmpleado(empleadoDtoRequest empleadoDtoRequest) {
		
		// el Request se combierte al modelo
		empleadoModel empleadoModel = this.modelMapper.map(empleadoDtoRequest, empleadoModel.class);
		
		empleadoModuloModel aux;
		List<empleadoModuloModel>listaEmpeladoModulo = this.empleadoModuloDao.getCif(empleadoModel.getCif());
		for(int i=0;i<listaEmpeladoModulo.size();i++) {
			aux = listaEmpeladoModulo.get(i);
			aux.setEstado(empleadoModel.getEstado());
			this.empleadoModuloDao.save(aux);
		}
		
		this.empleadoDao.save(empleadoModel);
		return this.modelMapper.map(empleadoModel, empleadoDtoResponse.class);
	}
	public List<empleadoObj> getListaEmpleado(Long tipo) {
		List<empleadoObj> listaEmpleadoObj = new ArrayList<empleadoObj>();
		List<empleadoModel>lE = this.empleadoDao.getListaEmpleado(tipo, 1); // Lista de Empleado que estan activados 
		empleadoObj empleadoObj;
		for(int i=0;i<lE.size();i++) {
			empleadoObj = new empleadoObj();
			empleadoObj.setId(lE.get(i).getId());
			empleadoObj.setCif(lE.get(i).getCif());
			empleadoObj.setEmpleado("");
			empleadoObj.setTipoempleado_id(lE.get(i).getTipo_empleado_id());
			empleadoObj.setFecha(lE.get(i).getFecha());
			empleadoObj.setEstado(lE.get(i).getEstado());
			empleadoObj.setSalida(lE.get(i).getSalida());
			listaEmpleadoObj.add(empleadoObj);
		}
		return(listaEmpleadoObj);
	}
	public empleadoObj getEmpleado(Long cif) {
		
		empleadoObj empleadoObj = new empleadoObj();
		
		empleadoModel empleadoModel=this.empleadoDao.getEmpleado(cif);
		if(empleadoModel==null)
		{
			empleadoObj.setId((long)0);
			return empleadoObj;
		}
		else {
			empleadoObj.setId(empleadoModel.getId());
			empleadoObj.setCif(cif);
			empleadoObj.setEstado(empleadoModel.getEstado());
			empleadoObj.setEmpleado(this.tipoempleadoDao.getTipoempleado(empleadoModel.getTipo_empleado_id()).getDetalle());
			
			empleadoObj.setTipoempleado_id(empleadoModel.getTipo_empleado_id());
			empleadoObj.setFecha(empleadoModel.getFecha());
			empleadoObj.setSalida(empleadoModel.getSalida());
			
			List<contratoDtoResponse>contratoObj=new ArrayList<contratoDtoResponse>();
			List<contratoDtoResponse>contratoDtoResponse = this.contratoDao.getContratos(cif).stream()
			        .map(contrato -> this.modelMapper.map(contrato, contratoDtoResponse.class))
			        .collect(Collectors.toList());
			for(int i=0;i<contratoDtoResponse.size();i++) {
				contratoDtoResponse auxXontrato=new contratoDtoResponse();
				auxXontrato.setId(contratoDtoResponse.get(i).getId());
				auxXontrato.setCif(contratoDtoResponse.get(i).getCif());
				auxXontrato.setNumero_contrato(contratoDtoResponse.get(i).getNumero_contrato());
				auxXontrato.setServicio(contratoDtoResponse.get(i).getServicio());
				auxXontrato.setUnidad(contratoDtoResponse.get(i).getUnidad());
				auxXontrato.setInicio(contratoDtoResponse.get(i).getInicio());
				auxXontrato.setFin(contratoDtoResponse.get(i).getFin());
				auxXontrato.setGestion(contratoDtoResponse.get(i).getGestion());
				auxXontrato.setDetalle(contratoDtoResponse.get(i).getDetalle());
				contratoObj.add(auxXontrato);
			}
			empleadoObj.setContratos(contratoObj);
			return empleadoObj;
		}
	}

}
