package com.fhce.emp.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class empleadoModuloDtoRequest {

	private Long cif;
	private Long id_modulo;
	private int estado;

}
