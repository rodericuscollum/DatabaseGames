package com.crudgames.spring.servicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudgames.spring.modelo.Neo_Geo;
import com.crudgames.spring.repositorios.Neo_GeoRepositorio;

@Service
public class Neo_GeoServicio {

	//private List<Neo_Geo> repositorio = new ArrayList<>();
	
	@Autowired
	private Neo_GeoRepositorio repositorio;
	
	public Neo_Geo add(Neo_Geo e) {
		repositorio.save(e);
		return e;
	}
	
	public List<Neo_Geo> findAll() {
		return (List<Neo_Geo>) repositorio.findAll();
	}
	
	public Neo_Geo findById(long id) {		
		return repositorio.findById(id).get();
	}
	
	public Neo_Geo edit(Neo_Geo e) {
		repositorio.save(e);		
		return e;
	}

    public void deleteNeo_Geo(long id) {
        repositorio.deleteById(id);
    }

}