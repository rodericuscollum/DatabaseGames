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
public class Atari_2600 {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	private String sears_title;
	private String code;
	private String designer_or_programer;
	private String publisher;
	private String year;
	private String genere;
	private String notes;
	private String image;
	
	public Atari_2600() {}


}
