package com.fhce.emp.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class empleadoDtoResponse {
	
	private Long id;
	private Long cif;
	private Long tipo_empleado_id;
	private String fecha;
	private int estado;
	private String salida;

}
