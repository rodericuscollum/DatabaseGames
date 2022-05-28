package com.crudgames.spring.servicios;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.crudgames.spring.modelo.User;
import com.crudgames.spring.registrationlogin.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{

	User save(UserRegistrationDto registrationDto);
	
}
