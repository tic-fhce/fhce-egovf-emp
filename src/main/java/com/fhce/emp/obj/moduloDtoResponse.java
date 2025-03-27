package com.fhce.emp.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class moduloDtoResponse {
	
	private Long id;
	private String nombre;
	private String ruta;
	private String imagen;
	private int idmenu;

}
