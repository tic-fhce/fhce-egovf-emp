package com.fhce.emp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.emp.obj.empleadoDtoRequest;
import com.fhce.emp.obj.empleadoDtoResponse;
import com.fhce.emp.obj.empleadoObj;
import com.fhce.emp.service.empleadoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf-emp/empleado")
@RequiredArgsConstructor
public class empleadoController {
	
	private final empleadoService empleadoServise;
	
	@GetMapping("/listar")
	public ResponseEntity <List<empleadoDtoResponse>> listar(){
		try {
			return new ResponseEntity<>(this.empleadoServise.listar(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getListaEmpleado")
	public ResponseEntity <List<empleadoObj>> getListaEmpleado(@RequestParam (value="tipo") Long tipo){
		try {
			return new ResponseEntity<>(this.empleadoServise.getListaEmpleado(tipo),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getEmpleados")
	public ResponseEntity <List<empleadoObj>> getEmpleados(){
		try {
			return new ResponseEntity<>(this.empleadoServise.getEmpleados(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/addEmpleado")
	public ResponseEntity<empleadoDtoResponse> addEmpleado(@RequestBody empleadoDtoRequest empleadoDtoRequest){
		try {
			return new ResponseEntity<>(this.empleadoServise.addEmpleado(empleadoDtoRequest),HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/updateEmpleado")
	public ResponseEntity<empleadoDtoResponse> updateEmpleado(@RequestBody empleadoDtoResponse empleadoDtoResponse){
		try {
			return new ResponseEntity<>(this.empleadoServise.updateEmpleado(empleadoDtoResponse),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/updateFoto")
	public ResponseEntity<empleadoDtoResponse> updateFoto(@RequestBody empleadoDtoResponse empleadoDtoResponse){
		try {
			return new ResponseEntity<>(this.empleadoServise.updateFoto(empleadoDtoResponse),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getEmpleado")
	public ResponseEntity<empleadoObj> getEmpleado(@RequestParam (value="cif") Long cif){
		try {
			return new ResponseEntity<>(this.empleadoServise.getEmpleado(cif),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
