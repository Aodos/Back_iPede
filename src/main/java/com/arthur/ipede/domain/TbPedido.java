package com.arthur.ipede.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class TbPedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer idPedido;
	private Integer idCliente;
	private Integer idRestaurante;
	private String nomeCliente;
	private String nomeRestaurante;
	private String valorTotal = "0.00";
	private Date dataPedido;
	private List<TbPedidoTemItem> itensPedido;
	
	public TbPedido() {
	}

	public TbPedido(Integer idPedido, Integer idCliente, Integer idRestaurante, String nomeCliente,
			String nomeRestaurante, String valorTotal, Date dataPedido, List<TbPedidoTemItem> itensPedido) {
		super();
		this.idPedido = idPedido;
		this.idCliente = idCliente;
		this.idRestaurante = idRestaurante;
		this.nomeCliente = nomeCliente;
		this.nomeRestaurante = nomeRestaurante;
		this.valorTotal = valorTotal;
		this.dataPedido = dataPedido;
		this.itensPedido = itensPedido;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(Integer idRestaurante) {
		this.idRestaurante = idRestaurante;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeRestaurante() {
		return nomeRestaurante;
	}

	public void setNomeRestaurante(String nomeRestaurante) {
		this.nomeRestaurante = nomeRestaurante;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public List<TbPedidoTemItem> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<TbPedidoTemItem> itensPedido) {
		this.itensPedido = itensPedido;
	}

	@Override
	public String toString() {
		return "TbPedido [idPedido=" + idPedido + ", idCliente=" + idCliente + ", idRestaurante=" + idRestaurante
				+ ", nomeCliente=" + nomeCliente + ", nomeRestaurante=" + nomeRestaurante + ", valorTotal=" + valorTotal
				+ ", dataPedido=" + dataPedido + ", itensPedido=" + itensPedido + "]";
	}
	

}
