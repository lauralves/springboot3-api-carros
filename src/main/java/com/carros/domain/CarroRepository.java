package com.carros.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CarroRepository extends CrudRepository<Carro, Long>{

	Iterable<Carro> findByTipo(String tipo);

}
