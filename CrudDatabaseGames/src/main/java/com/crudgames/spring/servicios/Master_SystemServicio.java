package com.crudgames.spring.servicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudgames.spring.modelo.Master_System;
import com.crudgames.spring.repositorios.Master_SystemRepositorio;

@Service
public class Master_SystemServicio {

	//private List<Master_System> repositorio = new ArrayList<>();
	
	@Autowired
	private Master_SystemRepositorio repositorio;
	
	public Master_System add(Master_System e) {
		repositorio.save(e);
		return e;
	}
	
	public List<Master_System> findAll() {
		return (List<Master_System>) repositorio.findAll();
	}
	
	public Master_System findById(long id) {		
		return repositorio.findById(id).get();
	}
	
	public Master_System edit(Master_System e) {
		repositorio.save(e);		
		return e;
	}

    public void deleteMaster_System(long id) {
        repositorio.deleteById(id);
    }

}
