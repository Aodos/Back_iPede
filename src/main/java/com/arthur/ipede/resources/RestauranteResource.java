package com.arthur.ipede.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arthur.ipede.domain.TbRestaurante;
import com.arthur.ipede.services.RestauranteService;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteResource {

	@Autowired
	private RestauranteService service;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List> retornaTodosRestaurantes() {
		return ResponseEntity.ok().body(service.todosOsRestaurantes());
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/proximos", method = RequestMethod.GET)
	public ResponseEntity<List> retornaTodosRestaurantesProximos(@RequestParam(value="lat")String lat, @RequestParam(value="lng")String lng) {
		return ResponseEntity.ok().body(service.todosOsRestaurantesPerto(lat, lng));
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<List> buscaPorId(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.buscaPorId(id));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> teste(@RequestBody TbRestaurante obj) {
		obj = service.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getIdRestaurante()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody TbRestaurante obj,@PathVariable Integer id){
		obj.setIdRestaurante(id);
		obj = service.atualiza(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.deleta(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
}
