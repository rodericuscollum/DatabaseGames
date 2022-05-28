package com.crudgames.spring.servicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudgames.spring.modelo.Amiga;
import com.crudgames.spring.repositorios.AmigaRepositorio;

@Service
public class AmigaServicio {

	//private List<Amiga> repositorio = new ArrayList<>();
	
	@Autowired
	private AmigaRepositorio repositorio;
	
	public Amiga add(Amiga e) {
		repositorio.save(e);
		return e;
	}
	
	public List<Amiga> findAll() {
		return (List<Amiga>) repositorio.findAll();
	}
	
	public Amiga findById(long id) {		
		return repositorio.findById(id).get();
	}
	
	public Amiga edit(Amiga e) {
		repositorio.save(e);		
		return e;
	}

    public void deleteAmiga(long id) {
        repositorio.deleteById(id);
    }

}


