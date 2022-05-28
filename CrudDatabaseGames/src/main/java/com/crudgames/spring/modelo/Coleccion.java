package com.crudgames.spring.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.transaction.Transactional;

import lombok.Data;

@Entity
@Transactional
@Data
public class Coleccion {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long id;
	
	private String title;
	
	@ManyToOne
	private  Amiga amiga;
	
	@ManyToOne
	private Amstrad_cpc amstrad_cpc;
	
	@ManyToOne
	private Atari_2600 atari_2600;
	
	@ManyToOne
	private Famicom famicom;
	
	@ManyToOne
	private Master_System master_system;
	
	@ManyToOne
	private Megadrive_Genesis megadrive_genesis;
	
	@ManyToOne
	private Neo_Geo_Pocket neo_geo_pocket;
	
	@ManyToOne
	private Neo_Geo neo_geo;
	
	//@ManyToOne
	//private User user;
	
	@ManyToOne
	private Nes nes;
	
	public Coleccion() {}


}
