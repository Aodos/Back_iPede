package com.arthur.ipede.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.QueryException;
import org.springframework.stereotype.Service;

import com.arthur.ipede.domain.TbRestaurante;
import com.arthur.ipede.repository.DAORestaurante;

@Service
public class RestauranteService {

	private DAORestaurante daoRest = new DAORestaurante();
	ResultSet result = null;

	public List<TbRestaurante> todosOsRestaurantes() {
		List<TbRestaurante> lista = new ArrayList<TbRestaurante>();
		result = daoRest.realizaQuery("SELECT * FROM iPedeData.tb_restaurante;");
		
		try {
			while(result.next()) {
				 int idRestaurante = result.getInt("id_restaurante");
				 String cep = result.getString("cep");
				 String cidade = result.getString("cidade");
				 String cnpjRestaurante = result.getString("cnpj_restaurante");
				 String endereco = result.getString("endereco");
				 float latitude = result.getFloat("latitude");
				 float longitude = result.getFloat("longitude");
			     String nomeRestaurante = result.getString("nome_restaurante");
				 String senhaRestaurante = result.getString("senha_restaurante");
				
				
				TbRestaurante rest = new TbRestaurante(idRestaurante, cep, cidade, cnpjRestaurante, endereco, latitude, longitude, nomeRestaurante, senhaRestaurante);
				lista.add(rest);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
		
	}

	public List<TbRestaurante> buscaPorId(Integer id) {
		List<TbRestaurante> lista = new ArrayList<TbRestaurante>();
		result = daoRest.realizaQuery("SELECT * FROM iPedeData.tb_restaurante where id_restaurante = "+ id + ";");
		
		
		try {
			while(result.next()) {
				 int idRestaurante = result.getInt("id_restaurante");
				 String cep = result.getString("cep");
				 String cidade = result.getString("cidade");
				 String cnpjRestaurante = result.getString("cnpj_restaurante");
				 String endereco = result.getString("endereco");
				 float latitude = result.getFloat("latitude");
				 float longitude = result.getFloat("longitude");
			     String nomeRestaurante = result.getString("nome_restaurante");
				 String senhaRestaurante = result.getString("senha_restaurante");
				
				
				TbRestaurante rest = new TbRestaurante(idRestaurante, cep, cidade, cnpjRestaurante, endereco, latitude, longitude, nomeRestaurante, senhaRestaurante);
				lista.add(rest);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(lista.isEmpty()) {
			throw new QueryException("Retorno da consulta por id: " + id + " esta vazio./nClasse do erro: "+TbRestaurante.class.getName());
		}
		
		return lista;
	}

}
