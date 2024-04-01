package com.fhce.emp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.emp.dao.contratoDao;
import com.fhce.emp.dao.empleadoDao;
import com.fhce.emp.dao.tipoempleadoDao;
import com.fhce.emp.model.contratoModel;
import com.fhce.emp.model.empleadoModel;
import com.fhce.emp.obj.contratoObj;
import com.fhce.emp.obj.empleadoObj;

@RestController
@RequestMapping("fhce-egovf-emp/empleado") //develop
//@RequestMapping("biometrico") //production
//@CrossOrigin("http://svfhce.umsa.bo/") //debelop Fhce
@CrossOrigin("http://192.168.31.45:8080/") //debelop house
public class empleadoController {
	
	@Autowired
	private empleadoDao empleadoDao;
	
	@Autowired
	private tipoempleadoDao tipoempleadoDao;

	@Autowired
	private contratoDao contratoDao;
	
	@GetMapping("/listar")
	public List<empleadoModel> listar(){
		
		return this.empleadoDao.findAll();
	}
	
	@GetMapping("/getListaEmpleado")
	public List<empleadoObj> getListaEmpleado(@RequestParam (value="tipo") Long tipo){
		List<empleadoObj> listaEmpleadoObj = new ArrayList<empleadoObj>();
		List<empleadoModel>lE = this.empleadoDao.getListaEmpleado(tipo, 1); // Lista de Empleado que estan activados 
		empleadoObj empleadoObj;
		for(int i=0;i<lE.size();i++) {
			empleadoObj = new empleadoObj(lE.get(i).getId(), lE.get(i).get_01cif(), "",lE.get(i).get_02tipo_empleado_id(), lE.get(i).get_03fecha(), lE.get(i).get_04estado(), lE.get(i).get_05salida());
			listaEmpleadoObj.add(empleadoObj);
		}
		return(listaEmpleadoObj);
	}
	
	@PostMapping("/addEmpleado")
	public void addEmpleado(@RequestBody empleadoModel empleadoModel) throws Exception {
		
		this.empleadoDao.save(empleadoModel);
	}
	
	@GetMapping("/getEmpleado")
	public empleadoObj getEmpleado(@RequestParam (value="cif") Long cif){
	
		empleadoObj empleadoObj=new empleadoObj((long) 0, cif, "",(long) 0, "", 0, "");
		empleadoModel empleadoModel=this.empleadoDao.getEmpleado(cif);
		if(empleadoModel==null)
		{
			return empleadoObj;
		}
		else {
			empleadoObj.setId(empleadoModel.getId());
			empleadoObj.setEstado(empleadoModel.get_04estado());
			empleadoObj.setEmpleado(this.tipoempleadoDao.getTipoempleado(empleadoModel.get_02tipo_empleado_id()).get_01detalle());
			empleadoObj.setTipoempleado_id(empleadoModel.get_02tipo_empleado_id());
			empleadoObj.setFecha(empleadoModel.get_03fecha());
			empleadoObj.setSalida(empleadoModel.get_05salida());
			
			List<contratoObj>contratoObj=new ArrayList<contratoObj>();
			List<contratoModel>contratoModel= this.contratoDao.getContratos(cif, empleadoObj.getId());
			for(int i=0;i<contratoModel.size();i++) {
				//Long id, String numeroContrato, String servicio, String unidad,String inicio, String fin, int gestion, String detalle
				contratoObj auxXontrato=new contratoObj(contratoModel.get(i).getId(),contratoModel.get(i).get_03numero_contrato(),contratoModel.get(i).get_04servicio(),contratoModel.get(i).get_05unidad(),contratoModel.get(i).get_06inicio(),contratoModel.get(i).get_07fin(),contratoModel.get(i).get_08gestion(),contratoModel.get(i).get_09detalle());
				contratoObj.add(auxXontrato);
			}
			empleadoObj.setContratos(contratoObj);
			return empleadoObj;
		}
		
	}

}
