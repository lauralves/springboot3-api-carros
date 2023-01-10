package com.carros.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarroService {
	@Autowired
	private CarroRepository rep;
	
	public Iterable<Carro> getCarros(){
		return rep.findAll();
	}
	
	public List<Carro> getCarro(){
		List<Carro> carros = new ArrayList<>();
		
		carros.add(new Carro(1L, "Fusca"));
		carros.add(new Carro(2L,"Chevrolet"));
		carros.add(new Carro(3L, "KKK"));
		return carros;
	}
}
