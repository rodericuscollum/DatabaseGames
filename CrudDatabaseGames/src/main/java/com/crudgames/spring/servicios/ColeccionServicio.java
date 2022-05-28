package com.crudgames.spring.servicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudgames.spring.modelo.Coleccion;
import com.crudgames.spring.repositorios.ColeccionRepositorio;

@Service
public class ColeccionServicio {

	//private List<Coleccion> repositorio = new ArrayList<>();
	
	@Autowired
	private ColeccionRepositorio repositorio;
	
	public Coleccion add(Coleccion e) {
		repositorio.save(e);
		return e;
	}
	
	public List<Coleccion> findAll() {
		return (List<Coleccion>) repositorio.findAll();
	}
	
	public Coleccion findById(long id) {		
		return repositorio.findById(id).get();
	}
	
	public Coleccion edit(Coleccion e) {
		repositorio.save(e);		
		return e;
	}

    public void deleteColeccion(long id) {
        repositorio.deleteById(id);
    }

}


