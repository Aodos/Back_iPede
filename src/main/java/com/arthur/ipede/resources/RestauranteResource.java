package com.arthur.ipede.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arthur.ipede.domain.Restaurante;
import com.arthur.ipede.services.RestauranteService;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteResource {

	@Autowired
	private RestauranteService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Restaurante> retornaTodosRestaurantes() {
		return service.retornaTodosRestaurantes();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> teste(@PathVariable Integer id) {
		Restaurante obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String saveRest1() {
		service.saveRestaurantes();
		return "Deu certo";
	}

}
