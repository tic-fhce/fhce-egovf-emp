package com.fhce.emp.obj;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class empleadoObj {
	
	private Long id;
	private Long cif;
	private String empleado;
	private Long tipoempleado_id;
	private String fecha;
	private int estado;
	private String salida;
	private String foto;
	private String cargo;
	private List<contratoDtoResponse>contratos;

}
