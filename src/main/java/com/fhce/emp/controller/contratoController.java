package com.fhce.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.emp.dao.contratoDao;
import com.fhce.emp.dao.empleadoDao;
import com.fhce.emp.dao.empleadomoduloDao;
import com.fhce.emp.model.contratoModel;
import com.fhce.emp.model.empleadoModel;
import com.fhce.emp.model.empleadomoduloModel;

@RestController
@RequestMapping("fhce-egovf-emp/contrato") //develop
//@RequestMapping("biometrico") //production
//@CrossOrigin("http://svfhce.umsa.bo/") //debelop Fhce
@CrossOrigin("http://172.16.14.91:8080/") //debelop house
public class contratoController {
	
	@Autowired
	private contratoDao contratoDao;
	
	@Autowired
	private empleadomoduloDao empleadomoduloDao;
	
	@Autowired
	private empleadoDao empleadoDao;
	
	@GetMapping("/listar")
	public List<contratoModel> listar(){
		
		return this.contratoDao.findAll();
	}
	
	@PostMapping("/addContrato")
	public void addContrato(@RequestBody contratoModel contratoModel) throws Exception {
		empleadoModel empleadoModel = this.empleadoDao.getEmpleado(contratoModel.get_01cif());
		if(empleadoModel.get_02tipo_empleado_id().longValue() == (long) 2 ){
			empleadomoduloModel empleadomoduloModel = this.empleadomoduloDao.getEmpleadoModulo(contratoModel.get_01cif());
			if(empleadomoduloModel == null) {
				empleadomoduloModel = new empleadomoduloModel();
				empleadomoduloModel.set_01cif(contratoModel.get_01cif());
				empleadomoduloModel.set_02id_modulo((long) 1);
				empleadomoduloModel.set_03estado(1);
				this.empleadomoduloDao.save(empleadomoduloModel);
			}
		}
		
		this.contratoDao.save(contratoModel);
	}
	
	@GetMapping("/listarContratos")
	public List<contratoModel> listarContratos(@RequestParam (value="cif") Long cif,@RequestParam (value="empleado") Long empleado){
		return this.contratoDao.getContratos(cif, empleado);
	}
	
	@PutMapping("/actualizarContrato")
	public void setContrato(@RequestBody contratoModel contratoModel)throws Exception{
		this.contratoDao.save(contratoModel);
	}

}
