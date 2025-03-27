package com.fhce.emp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.emp.obj.moduloDtoResponse;
import com.fhce.emp.service.moduloService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf-emp/modulo") //RequestMapping for Develop
@RequiredArgsConstructor
public class moduloController {
	
	private final moduloService moduloService;
	
	@GetMapping("/listar")
	public ResponseEntity<List<moduloDtoResponse>>listar(){
		try {
			return new ResponseEntity<>(this.moduloService.listar(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getListarEmpleadoModulo")
	public ResponseEntity<List<moduloDtoResponse>>listarEmpleadoModulo(@RequestParam (value="cif") Long cif){
		try {
			return new ResponseEntity<>(this.moduloService.getListarEmpleadoModulo(cif),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/getListarEmpleadoModuloCif")
	public ResponseEntity <List<moduloDtoResponse>>getListarModuloCif(@RequestParam (value="cif") Long cif){
		try {
			return new ResponseEntity<>(this.moduloService.getListarModuloCif(cif),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
