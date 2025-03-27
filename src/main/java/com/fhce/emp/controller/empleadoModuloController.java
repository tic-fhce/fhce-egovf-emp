package com.fhce.emp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.emp.obj.empleadoModuloDtoRequest;
import com.fhce.emp.obj.empleadoModuloDtoResponse;
import com.fhce.emp.service.empleadoModuloService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf-emp/empmodulo") //RequestMapping for Develop
@RequiredArgsConstructor
public class empleadoModuloController {
	
	private final empleadoModuloService empleadoModuloService;
	
	
	@PostMapping("/addHorario")
	public ResponseEntity<empleadoModuloDtoResponse> addHorario(@RequestBody empleadoModuloDtoRequest empleadoModuloDtoRequest){
		try {
			return new ResponseEntity<>(this.empleadoModuloService.addHorario(empleadoModuloDtoRequest),HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
