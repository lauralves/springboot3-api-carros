package com.carros;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.carros.domain.Carro;
import com.carros.domain.CarroService;
import com.carros.domain.dto.CarroDTO;
import com.carros.exception.ObjectNotFoundException;


@SpringBootTest
class CarrosApplicationTests {
	@Autowired
	private CarroService service;

	@Test
	public void teste1() {
		Carro carro = new Carro();
		carro.setNome("Ferrari");
		carro.setTipo("esportivos");
		
		CarroDTO c = service.insert(carro);
		
		assertNotNull(c);

		Long id = c.getId();
		assertNotNull(id);
		
		//Buscar o objeto
		c = service.getCarroById(id);
		assertNotNull(c);
		
		//verificando se os nomes sao iguais ao que esta no db
		assertEquals("Ferrari", c.getNome());
		assertEquals("esportivos", c.getTipo());
		
		//deletar o objeto
		service.delete(id);  
		//verificando se foi realmente deletado
		try {
			assertNull(service.getCarroById(id));
		} catch (ObjectNotFoundException ex) {
			//ok
		}
		
	}
	@Test
	public void testList() {
		List<CarroDTO> carros = service.getCarros();
		assertEquals(29, carros.size());
}
}
