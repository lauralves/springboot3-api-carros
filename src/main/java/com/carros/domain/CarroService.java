package com.carros.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.carros.domain.dto.CarroDTO;
import com.carros.exception.ObjectNotFoundException;

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
	public CarroDTO getCarroById(Long id){
		return rep.findById(id).map(c ->CarroDTO.create(c)).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado"));
	}
	
	public List<CarroDTO> getCarroByTipo(String tipo){
		return rep.findByTipo(tipo).stream().map(c-> CarroDTO.create(c)).collect(Collectors.toList());
	}
	
	public CarroDTO insert(Carro carro) {
		Assert.isNull(carro.getId(), "Não foi possível inserir o registro");
		return CarroDTO.create(rep.save(carro));
	}
	
	public CarroDTO update(Carro carro, Long id) {
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
			return CarroDTO.create(db);
		} else {
			return null;
			//throw new RuntimeException("Não foi possível atualizar o registro");
		}	
	}
	
	public void delete(Long id) {
			 rep.deleteById(id);
			
	}
	
	
}
