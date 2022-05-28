package com.crudgames.spring.servicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudgames.spring.modelo.Atari_2600;
import com.crudgames.spring.repositorios.Atari_2600Repositorio;

@Service
public class Atari_2600Servicio {

	//private List<Atari_2600> repositorio = new ArrayList<>();
	
	@Autowired
	private Atari_2600Repositorio repositorio;
	
	public Atari_2600 add(Atari_2600 e) {
		repositorio.save(e);
		return e;
	}
	
	public List<Atari_2600> findAll() {
		return (List<Atari_2600>) repositorio.findAll();
	}
	
	public Atari_2600 findById(long id) {		
		return repositorio.findById(id).get();
	}
	
	public Atari_2600 edit(Atari_2600 e) {
		repositorio.save(e);		
		return e;
	}

    public void deleteAtari_2600(long id) {
        repositorio.deleteById(id);
    }

}
