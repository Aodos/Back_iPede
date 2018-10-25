package com.arthur.ipede.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arthur.ipede.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResources {
	
	@Autowired
	private PedidoService service;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> criaPedido(@RequestParam(value="idCliente") Integer idCliente, @RequestParam(value="idRestaurante") Integer idRestaurante) {
		Integer idPedido = 	service.criaPedido(idCliente, idRestaurante);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id}").buildAndExpand(idPedido).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public ResponseEntity<Void> adicionaItemPedido(@PathVariable Integer id,
			@RequestParam(value="idItem") Integer idItem,
			@RequestParam(value="qnt") Integer qnt){
		service.adicionaItemAoPedido(id, idItem, qnt);
		return ResponseEntity.noContent().build();
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<List> buscaPedido(@PathVariable Integer id){		
		return ResponseEntity.ok().body(service.retornaPedido(id));
	}
	
}
