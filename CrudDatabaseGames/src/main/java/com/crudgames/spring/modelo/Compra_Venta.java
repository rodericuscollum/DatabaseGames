package com.crudgames.spring.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.transaction.Transactional;

import lombok.Data;

@Entity
@Transactional
@Data
public class Compra_Venta {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long id;
	private String compannia;
	private String sistema;
	private String articulo;
	@Column(columnDefinition = "TEXT")
	private String titulo;
	private String descripcion;
	private String estado;
	private String precio;
	private String image;
	
	public Compra_Venta() {}


}

