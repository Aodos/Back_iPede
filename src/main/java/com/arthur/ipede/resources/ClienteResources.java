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

import com.arthur.ipede.domain.TbCliente;
import com.arthur.ipede.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResources {

	@Autowired
	private ClienteService service;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List> retornaTodosRestaurantes() {
		return ResponseEntity.ok().body(service.todosOsClientes());
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<List> buscaPorId(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.buscaPorId(id));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> teste(@RequestBody TbCliente obj) {
		obj = service.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getIdt_id_cliente()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody TbCliente obj,@PathVariable Integer id){
		obj.setIdt_id_cliente(id);
		obj = service.atualiza(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.deleta(id);
		return ResponseEntity.noContent().build();
	}
}
