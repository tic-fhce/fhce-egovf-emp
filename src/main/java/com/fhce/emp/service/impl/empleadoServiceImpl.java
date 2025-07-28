package com.fhce.emp.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.emp.dao.contratoDao;
import com.fhce.emp.dao.empleadoDao;
import com.fhce.emp.dao.empleadoModuloDao;
import com.fhce.emp.dao.tipoEmpleadoDao;
import com.fhce.emp.model.contratoModel;
import com.fhce.emp.model.empleadoModel;
import com.fhce.emp.model.empleadoModuloModel;
import com.fhce.emp.model.tipoempleadoModel;
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
		empleadoModel empleadoModel = new empleadoModel();
		empleadoModel.setCif(empleadoDtoRequest.getCif());
		empleadoModel.setTipo_empleado_id(empleadoDtoRequest.getTipo_empleado_id());
		empleadoModel.setFecha(empleadoDtoRequest.getFecha());
		empleadoModel.setEstado(1);
		empleadoModel.setSalida(empleadoDtoRequest.getSalida());
		empleadoModel.setFoto("user.png");
		this.empleadoDao.save(empleadoModel);
		return this.modelMapper.map(empleadoModel, empleadoDtoResponse.class);
		
	}
	@Transactional
	public empleadoDtoResponse updateEmpleado(empleadoDtoResponse empleadoDtoResponse) {
		
		// el Request se combierte al modelo
		empleadoModel empleadoModel = new empleadoModel();
		empleadoModel.setId(empleadoDtoResponse.getId());
		empleadoModel.setCif(empleadoDtoResponse.getCif());
		empleadoModel.setTipo_empleado_id(empleadoDtoResponse.getTipo_empleado_id());
		empleadoModel.setFecha(empleadoDtoResponse.getFecha());
		empleadoModel.setEstado(empleadoDtoResponse.getEstado());
		empleadoModel.setSalida(empleadoDtoResponse.getSalida());
		empleadoModel.setFoto(empleadoDtoResponse.getFoto());
		
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
	@Transactional
	public empleadoDtoResponse updateFoto(empleadoDtoResponse empleadoDtoResponse) {
		
		// el Request se combierte al modelo
		empleadoModel empleadoModel = this.empleadoDao.getEmpleado(empleadoDtoResponse.getCif());
		empleadoModel.setFoto(empleadoDtoResponse.getFoto());
		this.empleadoDao.save(empleadoModel);
		return this.modelMapper.map(empleadoModel, empleadoDtoResponse.class);
	}
	public List<empleadoObj> getListaEmpleado(Long tipo) {
		
		List<empleadoObj> listaEmpleadoObj = new ArrayList<empleadoObj>();
		List<empleadoModel>lE = this.empleadoDao.getListaEmpleado(tipo, 1); // Lista de Empleados que estan activados 
		boolean verificador = false;
		empleadoObj empleadoObj;
		for(int i=0;i<lE.size();i++) {
			List<empleadoModuloModel>listaEmpleadoModulo = this.empleadoModuloDao.getListaModuloEmpleado(lE.get(i).getCif());
			verificador = false;
			for(int j=0;j<listaEmpleadoModulo.size();j++) {
				if(listaEmpleadoModulo.get(j).getId_modulo().longValue() == (long)1)
				{
					verificador = true;
					break;
				}
			}
			empleadoObj = new empleadoObj();
			empleadoObj.setId(lE.get(i).getId());
			empleadoObj.setCif(lE.get(i).getCif());
			empleadoObj.setEmpleado("");
			empleadoObj.setTipoempleado_id(lE.get(i).getTipo_empleado_id());
			empleadoObj.setFecha(lE.get(i).getFecha());
			empleadoObj.setEstado(lE.get(i).getEstado());
			empleadoObj.setSalida(lE.get(i).getSalida());
			if(verificador)
				empleadoObj.setEmpleado("Modulo Scc");
			
			listaEmpleadoObj.add(empleadoObj);
		}
		
		return(listaEmpleadoObj);
	}
	public List<empleadoObj> getEmpleados() {
		
		List<empleadoObj> listaEmpleadoObj = new ArrayList<empleadoObj>();
		List<empleadoModel>lE = this.empleadoDao.getListaEmpleado(1); // Lista de Empleados que estan activados
		List<tipoempleadoModel>tipoEmpleado = this.tipoempleadoDao.findAll();
		List<contratoModel>contrato;
		empleadoObj empleadoObj;
		for(int i=0;i<lE.size();i++) {
			empleadoObj = new empleadoObj();
			empleadoObj.setId(lE.get(i).getId());
			empleadoObj.setCif(lE.get(i).getCif());
			empleadoObj.setTipoempleado_id(lE.get(i).getTipo_empleado_id());
			empleadoObj.setEmpleado("");
			empleadoObj.setFecha(lE.get(i).getFecha());
			empleadoObj.setEstado(lE.get(i).getEstado());
			empleadoObj.setSalida(lE.get(i).getSalida());
			empleadoObj.setCargo("");
			empleadoObj.setFoto(lE.get(i).getFoto());
			
			for(int j=0;j<tipoEmpleado.size();j++) {
				if(tipoEmpleado.get(j).getId().longValue()==lE.get(i).getTipo_empleado_id()) {
					empleadoObj.setEmpleado(tipoEmpleado.get(j).getDetalle());
					break;
				}
			}
			contrato = this.contratoDao.getContratos(lE.get(i).getCif());
			if(contrato.size()>0) {
				Collections.sort(contrato, Comparator.comparingLong(contratoModel::getId).reversed());
				empleadoObj.setCargo(contrato.get(0).getCargo());
				List<contratoDtoResponse>contr=new ArrayList<contratoDtoResponse>();
				contratoDtoResponse c = new contratoDtoResponse();
				c.setId(contrato.get(0).getId());
				c.setCif(contrato.get(0).getCif());
				c.setNumero_contrato(contrato.get(0).getNumero_contrato()); 
				c.setServicio(contrato.get(0).getServicio());
				c.setUnidad(contrato.get(0).getUnidad());
				c.setInicio(contrato.get(0).getInicio());
				c.setFin(contrato.get(0).getFin()); 
				c.setGestion(contrato.get(0).getGestion());
				c.setDetalle(contrato.get(0).getDetalle());
				c.setIdTipoEmpleado(contrato.get(0).getIdTipoEmpleado());
				c.setCargo(contrato.get(0).getCargo());
				contr.add(c);
				empleadoObj.setContratos(contr);
			}
			
			listaEmpleadoObj.add(empleadoObj);
		}
		return(listaEmpleadoObj);
	}
	public empleadoObj getEmpleado(Long cif) {
		
		empleadoObj empleadoObj = new empleadoObj();
		empleadoModel empleadoModel=this.empleadoDao.getEmpleado(cif);
		String cargo="Sin cargo por asignar";
		if(empleadoModel==null)
		{
			empleadoObj.setId((long)0);
			return empleadoObj;
		}
		else {
			empleadoObj.setId(empleadoModel.getId());
			empleadoObj.setCif(cif);
			empleadoObj.setEstado(empleadoModel.getEstado());
			if(empleadoModel.getTipo_empleado_id()>0)
				empleadoObj.setEmpleado(this.tipoempleadoDao.getTipoempleado(empleadoModel.getTipo_empleado_id()).getDetalle());
			
			empleadoObj.setTipoempleado_id(empleadoModel.getTipo_empleado_id());
			empleadoObj.setFecha(empleadoModel.getFecha());
			empleadoObj.setSalida(empleadoModel.getSalida());
			empleadoObj.setFoto(empleadoModel.getFoto());
			
			List<contratoDtoResponse>contratoObj=new ArrayList<contratoDtoResponse>();
			List<contratoDtoResponse>contratoDtoR = this.contratoDao.getContratos(cif).stream()
			        .map(contrato -> this.modelMapper.map(contrato, contratoDtoResponse.class))
			        .collect(Collectors.toList());
			
			Collections.sort(contratoDtoR, Comparator.comparingLong(contratoDtoResponse::getId).reversed());
			for(int i=0;i<contratoDtoR.size();i++) {
				contratoDtoResponse auxXontrato=new contratoDtoResponse();
				auxXontrato.setId(contratoDtoR.get(i).getId());
				auxXontrato.setCif(contratoDtoR.get(i).getCif());
				auxXontrato.setNumero_contrato(contratoDtoR.get(i).getNumero_contrato());
				auxXontrato.setServicio(contratoDtoR.get(i).getServicio());
				auxXontrato.setUnidad(contratoDtoR.get(i).getUnidad());
				auxXontrato.setInicio(contratoDtoR.get(i).getInicio());
				auxXontrato.setFin(contratoDtoR.get(i).getFin());
				auxXontrato.setGestion(contratoDtoR.get(i).getGestion());
				auxXontrato.setDetalle(contratoDtoR.get(i).getDetalle());
				auxXontrato.setIdTipoEmpleado(contratoDtoR.get(i).getIdTipoEmpleado());
				auxXontrato.setCargo(contratoDtoR.get(i).getCargo());
				cargo=contratoDtoR.get(i).getCargo();
				contratoObj.add(auxXontrato);
			}
			
			empleadoObj.setCargo(cargo);
			empleadoObj.setContratos(contratoObj);
			return empleadoObj;
		}
	}

}
