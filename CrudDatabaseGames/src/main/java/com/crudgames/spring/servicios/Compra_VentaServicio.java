package com.crudgames.spring.servicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudgames.spring.modelo.Compra_Venta;
import com.crudgames.spring.repositorios.Compra_VentaRepositorio;

@Service
public class Compra_VentaServicio {

	//private List<Compra_Venta> repositorio = new ArrayList<>();
	
	@Autowired
	private Compra_VentaRepositorio repositorio;
	
	public Compra_Venta add(Compra_Venta e) {
		repositorio.save(e);
		return e;
	}
	
	public List<Compra_Venta> findAll() {
		return (List<Compra_Venta>) repositorio.findAll();
	}
	
	public Compra_Venta findById(long id) {		
		return repositorio.findById(id).get();
	}
	
	public Compra_Venta edit(Compra_Venta e) {
		repositorio.save(e);		
		return e;
	}

    public void deleteCompra_Venta(long id) {
        repositorio.deleteById(id);
    }

}