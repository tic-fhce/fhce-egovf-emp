package com.fhce.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.emp.dao.tipoempleadoDao;
import com.fhce.emp.model.tipoempleadoModel;

@RestController
@RequestMapping("fhce-egovf-emp/tipo") //develop
//@RequestMapping("biometrico") //production
//@CrossOrigin("http://svfhce.umsa.bo/") //debelop Fhce
@CrossOrigin("http://172.16.14.91:8080/") //debelop house
public class tipoempleadoController {
	
	@Autowired
	private tipoempleadoDao tipoempleadoDao;
	
	@GetMapping("/listar")
	public List<tipoempleadoModel> listar(){
		
		return this.tipoempleadoDao.findAll();
	}

}
