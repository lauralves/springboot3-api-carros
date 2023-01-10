package com.carros.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carros.domain.Carro;
import com.carros.domain.CarroService;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
	
	@Autowired
	private CarroService service;
	
	@GetMapping
	public Iterable<Carro> get(){
		return service.getCarros();
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity get(@PathVariable ("id") Long id){
		Optional<Carro> carro = service.getCarroById(id);
		
		return carro.isPresent() ? ResponseEntity.ok(carro.get()) : ResponseEntity.notFound().build();
		
		//return carro.map(c -> ResponseEntity.ok(c)).orElse(ResponseEntity.notFound().build());  UTILIZANDO LAMBDA EXPRESSION
 	}
	
	@GetMapping("/tipo/{tipo}")
	public Iterable<Carro> getCarroByTipo(@PathVariable ("tipo") String tipo) {
		return service.getCarroByTipo(tipo);
	}
	@PostMapping
	public String post(@RequestBody Carro carro) {
		Carro c = service.insert(carro);
		
		return "Carro salvo com sucesso. Id: " + c.getId();
	}
	
	@PutMapping("/{id}")
	public String put(@PathVariable("id") Long id, @RequestBody Carro carro) {
		Carro c = service.update(carro, id);
		
		return "Carro atualizado com sucesso! " + c.getId();
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {
		service.delete(id);
		
		return "Carro deletado com sucesso!";
	}
}
