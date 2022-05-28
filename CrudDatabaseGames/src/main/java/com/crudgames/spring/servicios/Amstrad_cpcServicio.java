package com.crudgames.spring.servicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudgames.spring.modelo.Amstrad_cpc;
import com.crudgames.spring.repositorios.Amstrad_cpcRepositorio;

@Service
public class Amstrad_cpcServicio {

	//private List<Amstrad_cpc> repositorio = new ArrayList<>();
	
	@Autowired
	private Amstrad_cpcRepositorio repositorio;
	
	public Amstrad_cpc add(Amstrad_cpc e) {
		repositorio.save(e);
		return e;
	}
	
	public List<Amstrad_cpc> findAll() {
		return (List<Amstrad_cpc>) repositorio.findAll();
	}
	
	public Amstrad_cpc findById(long id) {		
		return repositorio.findById(id).get();
	}
	
	public Amstrad_cpc edit(Amstrad_cpc e) {
		repositorio.save(e);		
		return e;
	}

    public void deleteAmstrad_cpc(long id) {
        repositorio.deleteById(id);
    }

}

