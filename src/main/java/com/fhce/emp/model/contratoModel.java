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
@Table(name = "contrato")
public class contratoModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	
	@Column(name = "_01cif")
	private Long cif;
	
	@Column(name = "_03numero_contrato")
	private String numero_contrato;
	
	@Column(name = "_04servicio")
	private String servicio;
	
	@Column (name = "_05unidad")
	private String unidad;
	
	@Column (name = "_06inicio")
	private String inicio;
	
	@Column (name = "_07fin")
	private String fin;
	
	@Column (name  = "_08gestion")
	private int gestion;
	
	@Column (name = "_09detalle")
	private String detalle;
	
	@Column (name = "_10idtipoempleado")
	private Long idTipoEmpleado;
}
