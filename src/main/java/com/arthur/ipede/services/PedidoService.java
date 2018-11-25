package com.arthur.ipede.services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.arthur.ipede.domain.TbPedido;
import com.arthur.ipede.domain.TbPedidoTemItem;
import com.arthur.ipede.repository.CriaConexao;
import com.arthur.ipede.services.exception.PedidoPendenteException;
import com.arthur.ipede.services.exception.QueryVaziaException;

@Service
public class PedidoService {

	private CriaConexao daoRest = new CriaConexao();
	private ResultSet result = null;
	private PreparedStatement preparedStatement = null;

	public boolean verificaSeNaoHaPedidoEmAberto(Integer idCliente) {
		try {
			result = daoRest.abreConexao().createStatement().executeQuery(
					"select * from ipededata.tb_pedido where sts_situacao_pagamento = 'Pendente' and fk_idt_id_cliente = "
							+ idCliente + ";");
			boolean aux = result.next();
			result.close();
			daoRest.close();

			return aux;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Integer criaPedido(Integer idCliente, Integer idRestaurante) {
		String criaPedido = "insert into ipededata.tb_pedido (dta_data_pedido, sts_situacao_pagamento, fk_idt_id_cliente, fk_idt_id_restaurante)\r\n"
				+ "values (now(), \'Pendente\', ?, ?);";

			try {
				
				if (verificaSeNaoHaPedidoEmAberto(idCliente)) {
					deletaPedido(idCliente);
				}
				
				preparedStatement = daoRest.abreConexao().prepareStatement(criaPedido,
						PreparedStatement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, idCliente);
				preparedStatement.setInt(2, idRestaurante);

				preparedStatement.execute();

				long key = 1L;
				ResultSet rs = preparedStatement.getGeneratedKeys();
				if (rs != null && rs.next()) {
					key = rs.getLong(1);
				}
				preparedStatement.close();
				daoRest.close();
				return (int) key;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		

		return null;
	}
	
	public void deletaPedido(Integer idCliente) {
		String query = "DELETE FROM ipededata.tb_pedido\r\n" + 
				"WHERE fk_idt_id_cliente = " + idCliente + " and sts_situacao_pagamento = \'Pendente\';";
		try {
			preparedStatement = daoRest.abreConexao().prepareStatement(query);

			preparedStatement.execute();

			preparedStatement.close();
			daoRest.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void adicionaItemAoPedido(Integer id, Integer idItem, Integer qnt) {
		String adicionaPedido = "insert into ipededata.ta_pedido_tem_item (fk_idt_id_pedido, fk_idt_id_item, qtd_item)\r\n"
				+ "values(?,?,?);";

		try {
			preparedStatement = daoRest.abreConexao().prepareStatement(adicionaPedido);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, idItem);
			preparedStatement.setInt(3, qnt);

			preparedStatement.execute();
			
			
			//pega o valor do pedido
			String update = "select sum(pti.qtd_item * i.vlr_valor_item) from ipededata.ta_pedido_tem_item pti\r\n" + 
					"INNER JOIN ipededata.tb_item i ON i.idt_id_item = pti.fk_idt_id_item\r\n" + 
					"where fk_idt_id_pedido = " + id + ";";
			
			result = daoRest.abreConexao().createStatement().executeQuery(update);
			float valorPedido = 0;
			if(result.next()) {
				valorPedido = result.getFloat(1);	
			}
			
			
			//Atualiza o valor do pedido
			update = "update ipededata.tb_pedido p set vlr_valor_total = ? where idt_id_pedido = " + id + ";";
			preparedStatement = daoRest.abreConexao().prepareStatement(update);
			preparedStatement.setString(1, Float.toString(valorPedido));
			preparedStatement.executeUpdate();
			
			result.close();
			preparedStatement.close();
			daoRest.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<TbPedido> retornaPedido(Integer id) {
		List<TbPedido> lista = new ArrayList<TbPedido>();

		String pedido = "select ipededata.tb_pedido.idt_id_pedido, ipededata.tb_cliente.idt_id_cliente, ipededata.tb_restaurante.idt_id_restaurante,\r\n"
				+ "ipededata.tb_cliente.nme_primeiro_nome, ipededata.tb_restaurante.nme_nome_restaurante, ipededata.tb_pedido.vlr_valor_total, ipededata.tb_pedido.dta_data_pedido\r\n"
				+ "from ipededata.tb_pedido\r\n"
				+ "INNER JOIN ipededata.tb_cliente on ipededata.tb_pedido.fk_idt_id_cliente = ipededata.tb_cliente.idt_id_cliente\r\n"
				+ "INNER JOIN ipededata.tb_restaurante on ipededata.tb_pedido.fk_idt_id_restaurante = ipededata.tb_restaurante.idt_id_restaurante\r\n"
				+ "where ipededata.tb_pedido.idt_id_pedido = " + id + ";";

		try {
			result = daoRest.abreConexao().createStatement().executeQuery(pedido);
			while (result.next()) {

				Integer idPedido = result.getInt("idt_id_pedido");
				Integer idCliente = result.getInt("idt_id_cliente");
				Integer idRestaurante = result.getInt("idt_id_restaurante");
				String nomeCliente = result.getString("nme_primeiro_nome");
				String nomeRestaurante = result.getString("nme_nome_restaurante");
				String valorTotal = result.getString("vlr_valor_total");
				Date dataPedido = result.getDate("dta_data_pedido");
				List<TbPedidoTemItem> itensPedido = retornaItensPedido(id);

				TbPedido pedi = new TbPedido(idPedido, idCliente, idRestaurante, nomeCliente, nomeRestaurante,
						valorTotal, dataPedido, itensPedido);

				lista.add(pedi);

			}
			result.close();
			daoRest.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (lista.isEmpty()) {
			throw new QueryVaziaException("Retorno da consulta por id: " + id + " esta vazio. Classe do erro: "
					+ PedidoService.class.getName());
		}

		return lista;
	}
	
	public List<TbPedido> retornaTodosOsPedido(Integer id) {

		List<TbPedido> lista = new ArrayList<TbPedido>();

		String todosOsPedidos = "select ipededata.tb_pedido.idt_id_pedido, ipededata.tb_cliente.idt_id_cliente, ipededata.tb_restaurante.idt_id_restaurante, ipededata.tb_cliente.nme_primeiro_nome, ipededata.tb_restaurante.nme_nome_restaurante, ipededata.tb_pedido.vlr_valor_total, ipededata.tb_pedido.dta_data_pedido from ipededata.tb_pedido INNER JOIN ipededata.tb_cliente on ipededata.tb_pedido.fk_idt_id_cliente = ipededata.tb_cliente.idt_id_cliente INNER JOIN ipededata.tb_restaurante on ipededata.tb_pedido.fk_idt_id_restaurante = ipededata.tb_restaurante.idt_id_restaurante where ipededata.tb_pedido.fk_idt_id_cliente = "+ id + " and ipededata.tb_pedido.sts_situacao_pagamento = 'Pago';";

		try {
			result = daoRest.abreConexao().createStatement().executeQuery(todosOsPedidos);
			while(result.next()) {
				
				Integer idPedido = result.getInt("idt_id_pedido");
				Integer idCliente = result.getInt("idt_id_cliente");
				Integer idRestaurante = result.getInt("idt_id_restaurante");
				String nomeCliente = result.getString("nme_primeiro_nome");
				String nomeRestaurante = result.getString("nme_nome_restaurante");
				String valorTotal = result.getString("vlr_valor_total");
				Date dataPedido = result.getDate("dta_data_pedido");
				List<TbPedidoTemItem> itensPedido = retornaItensPedido(idPedido);
				TbPedido pedi = new TbPedido(idPedido, idCliente, idRestaurante, nomeCliente, nomeRestaurante,
						valorTotal, dataPedido, itensPedido);
				
				lista.add(pedi);
				
			}
			result.close();
			daoRest.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (lista.isEmpty()) {
			throw new QueryVaziaException("Retorno da consulta por id: " + id + " esta vazio. Classe do erro: "
					+ PedidoService.class.getName());
		}

		return lista;
	}
	
	public List<TbPedidoTemItem> retornaItensPedido(Integer id) {
		CriaConexao daoResta = new CriaConexao();
		ResultSet resulta = null;
		List<TbPedidoTemItem> lista = new ArrayList<TbPedidoTemItem>();

		String pedido = "select ipededata.tb_item.idt_id_item, ipededata.tb_pedido.idt_id_pedido, ipededata.tb_item.nme_nome_item,\r\n" + 
				"ipededata.ta_pedido_tem_item.qtd_item, ipededata.tb_item.vlr_valor_item from ipededata.ta_pedido_tem_item\r\n" + 
				"INNER JOIN ipededata.tb_item on ipededata.ta_pedido_tem_item.fk_idt_id_item = ipededata.tb_item.idt_id_item\r\n" + 
				"INNER JOIN ipededata.tb_pedido on ipededata.ta_pedido_tem_item.fk_idt_id_pedido = ipededata.tb_pedido.idt_id_pedido\r\n" + 
				"where ipededata.ta_pedido_tem_item.fk_idt_id_pedido = " + id + ";";
		
		try {
			resulta = daoResta.abreConexao().createStatement().executeQuery(pedido);
			while (resulta.next()) {

				Integer idItem = resulta.getInt("idt_id_item");
				Integer idPedido = resulta.getInt("idt_id_pedido");
				String nomeItem = resulta.getString("nme_nome_item");
				Integer qntItem = resulta.getInt("qtd_item");
				float valorItem = resulta.getFloat("vlr_valor_item");

				TbPedidoTemItem pedi = new TbPedidoTemItem(idItem, idPedido, nomeItem, qntItem, valorItem);
				lista.add(pedi);

			}
			
			resulta.close();
			daoResta.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public void deletaItemPedido(Integer id, Integer idItem) {
		String delete = "DELETE FROM ipededata.ta_pedido_tem_item\r\n" + 
				"WHERE fk_idt_id_pedido = " + id + " and fk_idt_id_item = " + idItem + ";";
		try {
			preparedStatement = daoRest.abreConexao().prepareStatement(delete);
			preparedStatement.execute();
			
			//pega o valor do pedido
			String update = "select sum(pti.qtd_item * i.vlr_valor_item) from ipededata.ta_pedido_tem_item pti\r\n" + 
					"INNER JOIN ipededata.tb_item i ON i.idt_id_item = pti.fk_idt_id_item\r\n" + 
					"where fk_idt_id_pedido = " + id + ";";
			
			result = daoRest.abreConexao().createStatement().executeQuery(update);
			float valorPedido = 0;
			if(result.next()) {
				valorPedido = result.getFloat(1);	
			}
			
			
			//Atualiza o valor do pedido
			update = "update ipededata.tb_pedido p set vlr_valor_total = ? where idt_id_pedido = " + id + ";";
			preparedStatement = daoRest.abreConexao().prepareStatement(update);
			preparedStatement.setString(1, Float.toString(valorPedido));
			preparedStatement.executeUpdate();
			
			
			result.close();
			preparedStatement.close();
			daoRest.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

	public void zeraPedido(Integer id) {
		
		String criaPedido = "DELETE FROM ipededata.ta_pedido_tem_item\r\n" + 
				"WHERE fk_idt_id_pedido = " + id +";";

			try {
				
				preparedStatement = daoRest.abreConexao().prepareStatement(criaPedido);

				preparedStatement.execute();

				preparedStatement.close();
				daoRest.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public void finalizaPagamento(Integer id) {
		String realizaPgto = "update ipededata.tb_pedido p set sts_situacao_pagamento = 'Pago' where idt_id_pedido =" + id +";";

			try {
				
				preparedStatement = daoRest.abreConexao().prepareStatement(realizaPgto);

				preparedStatement.execute();

				preparedStatement.close();
				daoRest.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
	}
	

}
