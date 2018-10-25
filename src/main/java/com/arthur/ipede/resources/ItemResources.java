package com.arthur.ipede.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arthur.ipede.domain.TbItem;
import com.arthur.ipede.services.ItemService;

@RestController
@RequestMapping(value = "/itens-restaurante")
public class ItemResources {

	@Autowired
	private ItemService service;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<List> buscaPorIdRestaurante(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.buscaPorIdRestaurante(id));
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
	public ResponseEntity<List> buscaPorIdItem(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.buscaPorIdItem(id));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> teste(@RequestBody TbItem obj) {
		obj = service.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getIdt_id_item()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody TbItem obj,@PathVariable Integer id){
		obj.setIdt_id_item(id);
		obj = service.atualiza(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.deleta(id);
		return ResponseEntity.noContent().build();
	}
}
