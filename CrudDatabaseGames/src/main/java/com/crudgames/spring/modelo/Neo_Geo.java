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
public class Neo_Geo {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	

	private long id;
	private String title;
	private String genere;
	private String developer;
	private String publisher;
	private String mvs;
	private String aes;
	private String cd;
	private String image;
	
	public Neo_Geo() {}

}
