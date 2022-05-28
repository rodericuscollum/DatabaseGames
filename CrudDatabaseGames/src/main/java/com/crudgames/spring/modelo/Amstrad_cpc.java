package com.crudgames.spring.modelo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.transaction.Transactional;

import lombok.Data;


@Entity
@Transactional
@Data
public class Amstrad_cpc {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long id;
	private String title;
	private String release_date;
	private String publisher;
	private String image;
	
	public Amstrad_cpc() {}

	
}
