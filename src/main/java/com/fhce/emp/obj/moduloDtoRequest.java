package com.fhce.emp.obj;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class moduloDtoRequest {
	private String nombre;
	private String ruta;
	private String imagen;
	private int idmenu;
}
