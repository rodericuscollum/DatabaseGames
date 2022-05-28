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
public class Neo_Geo_Pocket {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private long id;
	private String title;
	private String developer;
	private String publisher;
	private String backwards_compatible;
	private String japan_release_date;
	private String north_america_release_date;
	private String pal_release_date;
	private String image;
	
	
	public Neo_Geo_Pocket() {}


}
