package com.crudgames.spring.servicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudgames.spring.modelo.Nes;
import com.crudgames.spring.repositorios.NesRepositorio;

@Service
public class NesServicio {

	//private List<Nes> repositorio = new ArrayList<>();
	
	@Autowired
	private NesRepositorio repositorio;
	
	public Nes add(Nes e) {
		repositorio.save(e);
		return e;
	}
	
	public List<Nes> findAll() {
		return (List<Nes>) repositorio.findAll();
	}
	
	public Nes findById(long id) {		
		return repositorio.findById(id).get();
	}
	
	public Nes edit(Nes e) {
		repositorio.save(e);		
		return e;
	}

    public void deleteNes(long id) {
        repositorio.deleteById(id);
    }

}