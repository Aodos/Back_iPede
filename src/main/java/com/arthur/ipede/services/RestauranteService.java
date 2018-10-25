package com.arthur.ipede.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.arthur.ipede.domain.TbRestaurante;
import com.arthur.ipede.repository.CriaConexao;
import com.arthur.ipede.services.exception.QueryVaziaException;

@Service
public class RestauranteService {

	private CriaConexao daoRest = new CriaConexao();
	private ResultSet result = null;
	private PreparedStatement preparedStatement = null;

	public List<TbRestaurante> todosOsRestaurantes() {
		List<TbRestaurante> lista = new ArrayList<TbRestaurante>();
		try {
			result = daoRest.abreConexao().createStatement().executeQuery("SELECT * FROM ipededata.tb_restaurante;");
			while (result.next()) {
				int idRestaurante = result.getInt("idt_id_restaurante");
				String cep = result.getString("cep_restaurante");
				String cidade = result.getString("end_cidade");
				String cnpjRestaurante = result.getString("cpj_cnpj_restaurante");
				String endereco = result.getString("end_endereco");
				float latitude = result.getFloat("lat_latitude");
				float longitude = result.getFloat("lgt_longitude");
				String nomeRestaurante = result.getString("nme_nome_restaurante");
				String senhaRestaurante = result.getString("pwd_senha_restaurante");
				String urlFotoRestaurante = result.getString("url_foto_restaurante");

				TbRestaurante rest = new TbRestaurante(idRestaurante, cep, cidade, cnpjRestaurante, endereco, latitude,
						longitude, nomeRestaurante, senhaRestaurante, urlFotoRestaurante);

				lista.add(rest);

			}
			result.close();
			daoRest.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;

	}

	public List<TbRestaurante> buscaPorId(Integer id) {
		List<TbRestaurante> lista = new ArrayList<TbRestaurante>();
		try {
			result = daoRest.abreConexao().createStatement()
					.executeQuery("SELECT * FROM ipededata.tb_restaurante where idt_id_restaurante = " + id + ";");
			while (result.next()) {

				int idRestaurante = result.getInt("idt_id_restaurante");
				String cep = result.getString("cep_restaurante");
				String cidade = result.getString("end_cidade");
				String cnpjRestaurante = result.getString("cpj_cnpj_restaurante");
				String endereco = result.getString("end_endereco");
				float latitude = result.getFloat("lat_latitude");
				float longitude = result.getFloat("lgt_longitude");
				String nomeRestaurante = result.getString("nme_nome_restaurante");
				String senhaRestaurante = result.getString("pwd_senha_restaurante");
				String urlFotoRestaurante = result.getString("url_foto_restaurante");

				TbRestaurante rest = new TbRestaurante(idRestaurante, cep, cidade, cnpjRestaurante, endereco, latitude,
						longitude, nomeRestaurante, senhaRestaurante, urlFotoRestaurante);
				lista.add(rest);

			}
			result.close();
			daoRest.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (lista.isEmpty()) {
			throw new QueryVaziaException("Retorno da consulta por id: " + id + " esta vazio. Classe do erro: "
					+ RestauranteService.class.getName());
		}

		return lista;
	}

	public TbRestaurante inserir(TbRestaurante obj) {
		String insert = "insert into ipededata.tb_restaurante (end_endereco, nme_nome_restaurante, end_cidade, cep_restaurante, pwd_senha_restaurante, cpj_cnpj_restaurante, lat_latitude, lgt_longitude)\r\n"
				+ "values (?, ?, ?, ?, ?, ?, ?, ?);";

		try {
			preparedStatement = daoRest.abreConexao().prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, obj.getEndereco());
			preparedStatement.setString(2, obj.getNomeRestaurante());
			preparedStatement.setString(3, obj.getCidade());
			preparedStatement.setString(4, obj.getCep());
			preparedStatement.setString(5, obj.getSenhaRestaurante());
			preparedStatement.setString(6, obj.getCnpjRestaurante());
			preparedStatement.setFloat(7, obj.getLatitude());
			preparedStatement.setFloat(8, obj.getLongitude());

			preparedStatement.execute();

			long key = 1L;
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs != null && rs.next()) {
				key = rs.getLong(1);
			}

			obj.setIdRestaurante((int) key);

			preparedStatement.close();
			daoRest.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return obj;
	}

	public TbRestaurante atualiza(TbRestaurante obj) {
		TbRestaurante restauranteBanco = this.buscaPorId(obj.getIdRestaurante()).get(0);
		
		
		obj = atualizaObj(obj, restauranteBanco);
		
		
		String update = "update ipededata.tb_restaurante set end_endereco = ?, nme_nome_restaurante = ?, end_cidade = ?, cep_restaurante = ?,"
				+ " pwd_senha_restaurante = ?, cpj_cnpj_restaurante = ?, lat_latitude = ?, lgt_longitude = ?, url_foto_restaurante = ? where idt_id_restaurante = ?;";

		try {
			preparedStatement = daoRest.abreConexao().prepareStatement(update);
			preparedStatement.setString(1, obj.getEndereco());
			preparedStatement.setString(2, obj.getNomeRestaurante());
			preparedStatement.setString(3, obj.getCidade());
			preparedStatement.setString(4, obj.getCep());
			preparedStatement.setString(5, obj.getSenhaRestaurante());
			preparedStatement.setString(6, obj.getCnpjRestaurante());
			preparedStatement.setFloat(7, obj.getLatitude());
			preparedStatement.setFloat(8, obj.getLongitude());
			preparedStatement.setString(9, obj.getUrl_foto_restaurante());
			preparedStatement.setInt(10, obj.getIdRestaurante());

			preparedStatement.executeUpdate();

			preparedStatement.close();
			daoRest.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return obj;
	}

	private TbRestaurante atualizaObj(TbRestaurante obj, TbRestaurante restauranteBanco) {

		if(obj.getEndereco() == null) {
			obj.setEndereco(restauranteBanco.getEndereco());
		}
		
		if(obj.getNomeRestaurante() == null) {
			obj.setNomeRestaurante(restauranteBanco.getNomeRestaurante());
		}
		
		if(obj.getCidade() == null) {
			obj.setCidade(restauranteBanco.getCidade());
		}
		
		if(obj.getCep() == null) {
			obj.setCep(restauranteBanco.getCep());
		}
		
		if(obj.getSenhaRestaurante() == null) {
			obj.setSenhaRestaurante(restauranteBanco.getSenhaRestaurante());
		}
		
		if(obj.getCnpjRestaurante() == null) {
			obj.setCnpjRestaurante(restauranteBanco.getCnpjRestaurante());
		}
		
		if(obj.getLatitude() == 0) {
			obj.setLatitude(restauranteBanco.getLatitude());
		}
		
		if(obj.getLongitude() == 0) {
			obj.setLongitude(restauranteBanco.getLongitude());
		}
		
		if(obj.getUrl_foto_restaurante() == null) {
			obj.setUrl_foto_restaurante(restauranteBanco.getUrl_foto_restaurante());
		}
		
		
		return obj;
	}

	public void deleta(Integer id) {
		String delete = "DELETE FROM ipededata.tb_restaurante\r\n" + "WHERE idt_id_restaurante = " + id + ";";

		try {
			preparedStatement = daoRest.abreConexao().prepareStatement(delete);
			preparedStatement.executeUpdate();

			preparedStatement.close();
			daoRest.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
