package com.arthur.ipede.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.arthur.ipede.domain.TbCliente;
import com.arthur.ipede.repository.CriaConexao;
import com.arthur.ipede.services.exception.QueryVaziaException;

@Service
public class ClienteService {

	private CriaConexao daoRest = new CriaConexao();
	private ResultSet result = null;
	private PreparedStatement preparedStatement = null;

	public List<TbCliente> todosOsClientes() {
		List<TbCliente> lista = new ArrayList<TbCliente>();
		try {
			result = daoRest.abreConexao().createStatement().executeQuery("SELECT * FROM ipededata.tb_cliente;");
			while(result.next()) {
				 int idCliente = result.getInt("idt_id_cliente");
				 String primeiroNome = result.getString("nme_primeiro_nome");
				 String ultimoNome = result.getString("nme_ultimo_nome");
				 String cpfCliente = result.getString("cpf_cliente");
				 String emailCliente = result.getString("eml_email");
				 String celular = result.getString("cel_celular");
				 String senhaCliente = result.getString("pwd_senha");
				 Integer ddd = result.getInt("ddd_ddd");
				 String urlFotoPerfil = result.getString("url_foto_cliente");
				 
				
				
				TbCliente rest = new TbCliente(idCliente, primeiroNome, ultimoNome, cpfCliente, emailCliente, celular, senhaCliente, ddd, urlFotoPerfil);
				
				lista.add(rest);
				
			}
			result.close();
			daoRest.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
		
	}

	public List<TbCliente> buscaPorId(Integer id) {
		List<TbCliente> lista = new ArrayList<TbCliente>();
		try {
			result = daoRest.abreConexao().createStatement().executeQuery("select * from ipededata.tb_cliente where idt_id_cliente = "+ id + ";");
			while(result.next()) {
				
				 int idCliente = result.getInt("idt_id_cliente");
				 String primeiroNome = result.getString("nme_primeiro_nome");
				 String ultimoNome = result.getString("nme_ultimo_nome");
				 String cpfCliente = result.getString("cpf_cliente");
				 String emailCliente = result.getString("eml_email");
				 String celular = result.getString("cel_celular");
				 String senhaCliente = result.getString("pwd_senha");
				 Integer ddd = result.getInt("ddd_ddd");
				 String urlFotoPerfil = result.getString("url_foto_cliente");
				
				
				 TbCliente rest = new TbCliente(idCliente, primeiroNome, ultimoNome, cpfCliente, emailCliente, celular, senhaCliente, ddd, urlFotoPerfil);
				lista.add(rest);
					
			}
			result.close();
			daoRest.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(lista.isEmpty()) {
			throw new QueryVaziaException("Retorno da consulta por id: " + id + " esta vazio. Classe do erro: "+ClienteService.class.getName());
		}
		
		return lista;
	}
	
	public TbCliente inserir(TbCliente obj ) {
		String insert = "insert into ipededata.tb_cliente (nme_primeiro_nome, nme_ultimo_nome, cpf_cliente, eml_email, cel_celular, pwd_senha, ddd_ddd)\r\n" + 
				"values (?, ?, ?, ?, ?, ?, ?);";
		
		try {
			preparedStatement = daoRest.abreConexao().prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, obj.getNme_primeiro_nome());
			preparedStatement.setString(2, obj.getNme_ultimo_nome());
			preparedStatement.setString(3, obj.getCpf_cliente());
			preparedStatement.setString(4, obj.getEml_email());
			preparedStatement.setString(5, obj.getCel_celular());
			preparedStatement.setString(6, obj.getPwd_senha());
			preparedStatement.setInt(7, obj.getDdd_ddd());
			
			
			preparedStatement.execute();
			
			long key = 1L;
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs != null && rs.next()) {
			    key = rs.getLong(1);
			}
			
			obj.setIdt_id_cliente((int)key);
			
			preparedStatement.close();
			daoRest.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return obj;
	}

	public TbCliente atualiza(TbCliente obj) {
		TbCliente clienteBanco = this.buscaPorId(obj.getIdt_id_cliente()).get(0);
		
		obj = atualizaObj(obj, clienteBanco);
		
		
		String update = "update ipededata.tb_cliente set nme_primeiro_nome = ?, nme_ultimo_nome = ?, cpf_cliente = ?, eml_email = ?,\r\n" + 
				"cel_celular = ?, pwd_senha = ?, ddd_ddd = ?, url_foto_cliente = ? where idt_id_cliente = ?;";
		
		try {
			preparedStatement = daoRest.abreConexao().prepareStatement(update);
			preparedStatement.setString(1, obj.getNme_primeiro_nome());
			preparedStatement.setString(2, obj.getNme_ultimo_nome());
			preparedStatement.setString(3, obj.getCpf_cliente());
			preparedStatement.setString(4, obj.getEml_email());
			preparedStatement.setString(5, obj.getCel_celular());
			preparedStatement.setString(6, obj.getPwd_senha());
			preparedStatement.setInt(7, obj.getDdd_ddd());
			preparedStatement.setString(8, obj.getUrl_foto_cliente());
			preparedStatement.setInt(9, obj.getIdt_id_cliente());
			
			
			
			preparedStatement.executeUpdate();
			
			
			preparedStatement.close();
			daoRest.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return obj;
	}
	
	private TbCliente atualizaObj(TbCliente obj, TbCliente clienteBanco) {
		if(obj.getNme_primeiro_nome() == null) {
			obj.setNme_primeiro_nome(clienteBanco.getNme_primeiro_nome());
		}
		
		if(obj.getNme_ultimo_nome() == null) {
			obj.setNme_ultimo_nome(clienteBanco.getNme_ultimo_nome());
		}
		
		if(obj.getCpf_cliente() == null) {
			obj.setCpf_cliente(clienteBanco.getCpf_cliente());
		}
		
		if(obj.getEml_email() == null) {
			obj.setEml_email(clienteBanco.getEml_email());
		}
		
		if(obj.getCel_celular() == null) {
			obj.setCel_celular(clienteBanco.getCel_celular());
		}
		
		if(obj.getPwd_senha() == null) {
			obj.setPwd_senha(clienteBanco.getPwd_senha());
		}
		
		if(obj.getDdd_ddd() == null) {
			obj.setDdd_ddd(clienteBanco.getDdd_ddd());
		}
		
		if(obj.getUrl_foto_cliente() == null) {
			obj.setUrl_foto_cliente(clienteBanco.getUrl_foto_cliente());
		}
		

		return obj;
	}

	public void deleta(Integer id) {
		this.buscaPorId(id);
		String delete = "DELETE FROM ipededata.tb_cliente\r\n" + 
				"WHERE idt_id_cliente = "+ id + ";";
		
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
