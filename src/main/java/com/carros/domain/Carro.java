package com.carros.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity(name = "carro")
@Data  //getters, setters, ToString e HashCode e Equals
public class Carro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String descricao;
	private String urlFoto;
	private String urlVideo;
	private String latitude;
	private String longitude;
	private String tipo;
	
}
