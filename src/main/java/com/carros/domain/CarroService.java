package com.carros.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.carros.domain.dto.CarroDTO;

@Service
public class CarroService {
	@Autowired
	private CarroRepository rep;
	
	public List<CarroDTO> getCarros(){
		List<Carro> carros = rep.findAll();
	
		List<CarroDTO> list = carros.stream().map(c -> CarroDTO.create(c)).collect(Collectors.toList()); //utilizando lambda expression
		
		//utilizando for each
		
		//for (Carro c: carros) {
			//list.add(new CarroDTO(c));
		//}
		
		return list;
	}
	public Optional <CarroDTO> getCarroById(Long id){
		return rep.findById(id).map(c ->CarroDTO.create(c));
	}
	
	public List<CarroDTO> getCarroByTipo(String tipo){
		return rep.findByTipo(tipo).stream().map(c-> CarroDTO.create(c)).collect(Collectors.toList());
	}
	
	public CarroDTO insert(Carro carro) {
		Assert.isNull(carro.getId(), "Não foi possível inserir o registro");
		return CarroDTO.create(rep.save(carro));
	}
	
	public Carro update(Carro carro, Long id) {
		Assert.notNull(id, "Não foi possível atualizar o registro");
		//busca o carro no db
		Optional<Carro> carroId = rep.findById(id);
		if (carroId.isPresent()) {
			Carro db = carroId.get();
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id: " + db.getId());
			
			//atualiza o carro
			
			rep.save(db);
			return db;
		} else {
			throw new RuntimeException("Não foi possível atualizar o registro");
		}	
	}
	
	public void delete(Long id) {
		if(getCarroById(id).isPresent()) {
			rep.deleteById(id);
		}
	}
	
	
}
