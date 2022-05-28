package com.crudgames.spring.servicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudgames.spring.modelo.Neo_Geo_Pocket;
import com.crudgames.spring.repositorios.Neo_Geo_PocketRepositorio;

@Service
public class Neo_Geo_PocketServicio {

	//private List<Neo_Geo_Pocket> repositorio = new ArrayList<>();
	
	@Autowired
	private Neo_Geo_PocketRepositorio repositorio;
	
	public Neo_Geo_Pocket add(Neo_Geo_Pocket e) {
		repositorio.save(e);
		return e;
	}
	
	public List<Neo_Geo_Pocket> findAll() {
		return (List<Neo_Geo_Pocket>) repositorio.findAll();
	}
	
	public Neo_Geo_Pocket findById(long id) {		
		return repositorio.findById(id).get();
	}
	
	public Neo_Geo_Pocket edit(Neo_Geo_Pocket e) {
		repositorio.save(e);		
		return e;
	}

    public void deleteNeo_Geo_Pocket(long id) {
        repositorio.deleteById(id);
    }

}
