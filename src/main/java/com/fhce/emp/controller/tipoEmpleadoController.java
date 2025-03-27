package com.fhce.emp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.emp.obj.tipoEmpleadoDtoResponse;
import com.fhce.emp.service.tipoEmpleadoService;
import com.fhce.emp.service.impl.tipoEmpleadoServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf-emp/tipo") //develop
@RequiredArgsConstructor
public class tipoEmpleadoController {
	
	private final tipoEmpleadoService tipoEmpleadoService;
	
	@GetMapping("/listar")
	public ResponseEntity <List<tipoEmpleadoDtoResponse>> listar(){
		try {
			return new ResponseEntity<>(this.tipoEmpleadoService.listar(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getTipoEmpleado")
	public ResponseEntity<tipoEmpleadoDtoResponse> getTipoEmpleado(@RequestParam (value="id") Long id) {
		try {
			return new ResponseEntity<>(this.tipoEmpleadoService.getTipoEmpleado(id),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
