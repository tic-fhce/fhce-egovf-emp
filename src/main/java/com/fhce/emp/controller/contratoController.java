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

import com.fhce.emp.obj.contratoDtoRequest;
import com.fhce.emp.obj.contratoDtoResponse;
import com.fhce.emp.service.contratoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf-emp/contrato")
@RequiredArgsConstructor
public class contratoController {
	
	private final contratoService contratoService;
	
	@GetMapping("/listar")
	public ResponseEntity <List<contratoDtoResponse>> listar(){
		try {
			return new ResponseEntity<>(this.contratoService.listar(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/listarContratos")
	public ResponseEntity <List<contratoDtoResponse>>listarContratos(@RequestParam (value="cif") Long cif){
		try {
			return new ResponseEntity<>(this.contratoService.listarContratos(cif),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/addContrato")
	public ResponseEntity <contratoDtoResponse>addContrato(@RequestBody contratoDtoRequest contratoDtoRequest){
		try {
			return new ResponseEntity<>(this.contratoService.addContrato(contratoDtoRequest),HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/updateContrato")
	public ResponseEntity <contratoDtoResponse>updateContrato(@RequestBody contratoDtoResponse contratoDtoResponse){
		try {
			return new ResponseEntity<>(this.contratoService.updateContrato(contratoDtoResponse),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
