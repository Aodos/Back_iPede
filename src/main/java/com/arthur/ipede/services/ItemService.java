package com.arthur.ipede.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.arthur.ipede.domain.TbItem;
import com.arthur.ipede.repository.CriaConexao;
import com.arthur.ipede.services.exception.QueryVaziaException;

@Service
public class ItemService {

	private CriaConexao daoRest = new CriaConexao();
	private ResultSet result = null;
	private PreparedStatement preparedStatement = null;

	public List<TbItem> buscaPorIdRestaurante(Integer id) {
		List<TbItem> lista = new ArrayList<TbItem>();
		try {
			result = daoRest.abreConexao().createStatement().executeQuery("select * from ipededata.tb_item where fk_idt_id_restaurante = "+ id + ";");
			while(result.next()) {
				
				 int idt_id_item = result.getInt("idt_id_item");
				 String nme_nome_item = result.getString("nme_nome_item");
				 String dsc_descricao_item = result.getString("dsc_descricao_item");
				 float vlr_valor_item = result.getFloat("vlr_valor_item");
				 Integer flg_disponibilidade_item = result.getInt("flg_disponibilidade_item");
				 String url_foto_item = result.getString("url_foto_item");
				 Integer fk_idt_id_restaurante = result.getInt("fk_idt_id_restaurante");
				
				
				 TbItem rest = new TbItem(idt_id_item, nme_nome_item, dsc_descricao_item, vlr_valor_item, flg_disponibilidade_item, url_foto_item, fk_idt_id_restaurante);
				lista.add(rest);
					
			}
			result.close();
			daoRest.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(lista.isEmpty()) {
			throw new QueryVaziaException("Retorno da consulta por id: " + id + " esta vazio. Classe do erro: "+ItemService.class.getName());
		}
		
		return lista;
	}
	
	public List<TbItem> buscaPorIdItem(Integer id) {
		List<TbItem> lista = new ArrayList<TbItem>();
		try {
			result = daoRest.abreConexao().createStatement().executeQuery("select * from ipededata.tb_item where idt_id_item = "+ id + ";");
			while(result.next()) {
				
				 int idt_id_item = result.getInt("idt_id_item");
				 String nme_nome_item = result.getString("nme_nome_item");
				 String dsc_descricao_item = result.getString("dsc_descricao_item");
				 float vlr_valor_item = result.getFloat("vlr_valor_item");
				 Integer flg_disponibilidade_item = result.getInt("flg_disponibilidade_item");
				 String url_foto_item = result.getString("url_foto_item");
				 Integer fk_idt_id_restaurante = result.getInt("fk_idt_id_restaurante");
				
				
				 TbItem rest = new TbItem(idt_id_item, nme_nome_item, dsc_descricao_item, vlr_valor_item, flg_disponibilidade_item, url_foto_item, fk_idt_id_restaurante);
				lista.add(rest);
					
			}
			result.close();
			daoRest.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(lista.isEmpty()) {
			throw new QueryVaziaException("Retorno da consulta por id: " + id + " esta vazio. Classe do erro: "+ItemService.class.getName());
		}
		
		return lista;
	}
	
	public TbItem inserir(TbItem obj ) {
		String insert = "insert into ipededata.tb_item (nme_nome_item, dsc_descricao_item, vlr_valor_item, flg_disponibilidade_item, url_foto_item, fk_idt_id_restaurante)\r\n" + 
				"value (?,?,?,?,?,?);";
		
		try {
			preparedStatement = daoRest.abreConexao().prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, obj.getNme_nome_item());
			preparedStatement.setString(2, obj.getDsc_descricao_item());
			preparedStatement.setFloat(3, obj.getVlr_valor_item());
			preparedStatement.setInt(4, obj.getFlg_disponibilidade_item());
			preparedStatement.setString(5, obj.getUrl_foto_item());
			preparedStatement.setInt(6, obj.getFk_idt_id_restaurante());
			
			
			preparedStatement.execute();
			
			long key = 1L;
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs != null && rs.next()) {
			    key = rs.getLong(1);
			}
			
			obj.setIdt_id_item((int)key);
			
			preparedStatement.close();
			daoRest.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return obj;
	}

	public TbItem atualiza(TbItem obj) {
		TbItem itemBanco = this.buscaPorIdItem(obj.getIdt_id_item()).get(0);
		obj = atualizaObj(obj, itemBanco);
		
		
		
		String update = "update ipededata.tb_item set nme_nome_item = ?, dsc_descricao_item = ?, vlr_valor_item = ?, flg_disponibilidade_item = ?, url_foto_item = ?, fk_idt_id_restaurante = ?\r\n" + 
				"where idt_id_item = ?;";
		
		try {
			preparedStatement = daoRest.abreConexao().prepareStatement(update);
			preparedStatement.setString(1, obj.getNme_nome_item());
			preparedStatement.setString(2, obj.getDsc_descricao_item());
			preparedStatement.setFloat(3, obj.getVlr_valor_item());
			preparedStatement.setInt(4, obj.getFlg_disponibilidade_item());
			preparedStatement.setString(5, obj.getUrl_foto_item());
			preparedStatement.setInt(6, obj.getFk_idt_id_restaurante());
			preparedStatement.setInt(7, obj.getIdt_id_item());
			
			preparedStatement.executeUpdate();
			
			
			preparedStatement.close();
			daoRest.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return obj;
	}
	
	private TbItem atualizaObj(TbItem obj, TbItem itemBanco) {
		
		
		if(obj.getNme_nome_item() == null) {
			obj.setNme_nome_item(itemBanco.getNme_nome_item());
		}
		
		if(obj.getDsc_descricao_item() == null) {
			obj.setDsc_descricao_item(itemBanco.getDsc_descricao_item());
		}
		
		if(obj.getVlr_valor_item() == 0) {
			obj.setVlr_valor_item(itemBanco.getVlr_valor_item());
		}
		
		if(obj.getFlg_disponibilidade_item() == null) {
			obj.setFlg_disponibilidade_item(itemBanco.getFlg_disponibilidade_item());
		}
		
		if(obj.getUrl_foto_item() == null) {
			obj.setUrl_foto_item(itemBanco.getUrl_foto_item());
		}
		
		if(obj.getFk_idt_id_restaurante() == null) {
			obj.setFk_idt_id_restaurante(itemBanco.getFk_idt_id_restaurante());
		}
		
		return obj;
	}

	public void deleta(Integer id) {
		this.buscaPorIdItem(id);
		String delete = "DELETE FROM ipededata.tb_item\r\n" + 
				"WHERE idt_id_item = " + id + ";";
		
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
