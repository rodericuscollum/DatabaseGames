package com.crudgames.spring.servicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudgames.spring.modelo.User;
import com.crudgames.spring.repositorios.UserRepositorio;

@Service
public class UserServicio {

	//private List<User> repositorio = new ArrayList<>();
	
	@Autowired
	private UserRepositorio repositorio;
	
	public User add(User e) {
		repositorio.save(e);
		return e;
	}
	
	public List<User> findAll() {
		return (List<User>) repositorio.findAll();
	}
	
	public User findById(long id) {		
		return repositorio.findById(id).get();
	}
	
	public User edit(User e) {
		repositorio.save(e);		
		return e;
	}

    public void deleteUser(long id) {
        repositorio.deleteById(id);
    }

}