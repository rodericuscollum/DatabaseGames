package com.crudgames.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crudgames.spring.modelo.Coleccion;
import com.crudgames.spring.modelo.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	User findByEmail(String email);
	
	Coleccion findFirstById(String id);
	
}
