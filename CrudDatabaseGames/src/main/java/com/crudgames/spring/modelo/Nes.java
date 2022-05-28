package com.crudgames.spring.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.transaction.Transactional;

import lombok.Data;

@Data
@Entity
@Transactional
public class Nes {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	
	private long id;
	private String title;
	private String developer;
	private String worldwide_publisher;
	private String north_america_publisher;
	private String pal_publisher;
	private String north_america_release_date;
	private String pal_release_date;
	private String worldwide_release;
	private String notes;
	private String cancellation_date;
	private String image;
	
	public Nes() {}


}
