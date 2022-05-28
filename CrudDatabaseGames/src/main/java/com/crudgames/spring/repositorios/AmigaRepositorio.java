package com.crudgames.spring.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.crudgames.spring.modelo.Amiga;

public interface AmigaRepositorio extends CrudRepository<Amiga,Long> {
	
	Amiga findFirstById(String id);
	
}


