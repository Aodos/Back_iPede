package com.arthur.ipede.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arthur.ipede.domain.TbRestaurante;
import com.arthur.ipede.services.RestauranteService;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteResource {

	@Autowired
	private RestauranteService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<TbRestaurante> retornaTodosRestaurantes() {
		return service.todosOsRestaurantes();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public List<TbRestaurante> buscaPorId(@PathVariable Integer id) {
		return service.buscaPorId(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void teste(@RequestBody TbRestaurante obj) {
		System.out.println(obj.toString());
		System.out.println(obj.getNomeRestaurante());
	}
}
