package com.crudgames.spring.servicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudgames.spring.modelo.Megadrive_Genesis;
import com.crudgames.spring.repositorios.Megadrive_GenesisRepositorio;

@Service
public class Megadrive_GenesisServicio {

	//private List<Megadrive_Genesis> repositorio = new ArrayList<>();
	
	@Autowired
	private Megadrive_GenesisRepositorio repositorio;
	
	public Megadrive_Genesis add(Megadrive_Genesis e) {
		repositorio.save(e);
		return e;
	}
	
	public List<Megadrive_Genesis> findAll() {
		return (List<Megadrive_Genesis>) repositorio.findAll();
	}
	
	public Megadrive_Genesis findById(long id) {		
		return repositorio.findById(id).get();
	}
	
	public Megadrive_Genesis edit(Megadrive_Genesis e) {
		repositorio.save(e);		
		return e;
	}

    public void deleteMegadrive_Genesis(long id) {
        repositorio.deleteById(id);
    }

}
