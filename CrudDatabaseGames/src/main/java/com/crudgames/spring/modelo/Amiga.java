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
public class Amiga {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long id;
	private String title;
	private String chip;
	private String year;
	private String developer;
	private String publisher;
	private String image;
	
	public Amiga() {}


}
