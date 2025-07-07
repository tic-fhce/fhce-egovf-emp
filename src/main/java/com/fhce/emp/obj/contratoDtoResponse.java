package com.fhce.emp.obj;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class contratoDtoResponse {
	private Long id;
	private Long cif;
	private String numero_contrato;
	private String servicio;
	private String unidad;
	private String inicio;
	private String fin;
	private int gestion;
	private String detalle;
	private Long idTipoEmpleado;
	private String foto;
	private String cargo;
	

}
