package com.fhce.emp.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class tipoEmpleadoDtoRequest {
	private String detalle;
	private String corto;
	private String imagen;

}
