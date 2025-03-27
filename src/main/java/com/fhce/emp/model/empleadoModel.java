package com.fhce.emp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="empleado")

public class empleadoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column (name = "_01cif")
	private Long cif;
	
	@Column (name = "_02tipo_empleado_id")
	private Long tipo_empleado_id;
	
	@Column (name = "_03fecha")
	private String fecha;
	
	@Column (name = "_04estado")
	private int estado;
	
	@Column (name = "_05salida")
	private String salida;

}
