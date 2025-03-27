package com.fhce.emp.obj;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class tipoEmpleadoDtoResponse {
	private Long id;
	private String detalle;
	private String corto;
	private String imagen;
}
