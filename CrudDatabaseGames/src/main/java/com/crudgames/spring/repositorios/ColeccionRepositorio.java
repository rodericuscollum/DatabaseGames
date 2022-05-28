package com.crudgames.spring.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.crudgames.spring.modelo.Coleccion;

public interface ColeccionRepositorio extends CrudRepository<Coleccion,Long> {
	
	Coleccion findFirstById(String id);
	
}


