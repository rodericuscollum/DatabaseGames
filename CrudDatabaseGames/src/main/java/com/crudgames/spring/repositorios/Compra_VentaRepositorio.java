package com.crudgames.spring.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.crudgames.spring.modelo.Compra_Venta;

public interface Compra_VentaRepositorio extends CrudRepository<Compra_Venta,Long> {
	
	Compra_Venta findFirstById(String id);
	
}


