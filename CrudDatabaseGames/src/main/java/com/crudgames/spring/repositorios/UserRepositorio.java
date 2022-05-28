package com.crudgames.spring.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.crudgames.spring.modelo.Coleccion;
import com.crudgames.spring.modelo.User;

public interface  UserRepositorio extends CrudRepository<User,Long> {
	
	Coleccion findFirstById(String id);
	
 }






