package com.arthur.ipede.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arthur.ipede.domain.Restaurante;
import com.arthur.ipede.repository.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository repo;

	public Restaurante buscar(Integer id) {
		Optional<Restaurante> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
	public List<Restaurante> retornaTodosRestaurantes(){
		return repo.findAll();
	}
	
	public void saveRestaurantes() {
		Restaurante rest1 = new Restaurante(null, "McDonalds", "Taguatinga Shop", "Taguatinga", "72222-022", "25,2555", "36,22");
		repo.save(rest1);
	}
}
