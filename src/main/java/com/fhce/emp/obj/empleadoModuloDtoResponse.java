package com.fhce.emp.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class empleadoModuloDtoResponse {
	private Long id;
	private Long cif;
	private Long id_modulo;
	private int estado;

}
