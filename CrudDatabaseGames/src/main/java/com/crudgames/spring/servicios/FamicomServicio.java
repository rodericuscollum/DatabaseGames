package com.crudgames.spring.servicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudgames.spring.modelo.Famicom;
import com.crudgames.spring.repositorios.FamicomRepositorio;

@Service
public class FamicomServicio {

	//private List<Famicom> repositorio = new ArrayList<>();
	
	@Autowired
	private FamicomRepositorio repositorio;
	
	public Famicom add(Famicom e) {
		repositorio.save(e);
		return e;
	}
	
	public List<Famicom> findAll() {
		return (List<Famicom>) repositorio.findAll();
	}
	
	public Famicom findById(long id) {		
		return repositorio.findById(id).get();
	}
	
	public Famicom edit(Famicom e) {
		repositorio.save(e);		
		return e;
	}

    public void deleteFamicom(long id) {
        repositorio.deleteById(id);
    }

}
