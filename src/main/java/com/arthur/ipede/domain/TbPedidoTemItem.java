package com.arthur.ipede.domain;

import java.io.Serializable;

public class TbPedidoTemItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer idItem;
	private Integer idPedido;
	private String nomeItem;
	private Integer qntItem;
	private float valorItem;
	
	public TbPedidoTemItem() {
	}

	public TbPedidoTemItem(Integer idItem, Integer idPedido, String nomeItem, Integer qntItem, float valorItem) {
		super();
		this.idItem = idItem;
		this.idPedido = idPedido;
		this.nomeItem = nomeItem;
		this.qntItem = qntItem;
		this.valorItem = valorItem;
	}

	public Integer getIdItem() {
		return idItem;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public String getNomeItem() {
		return nomeItem;
	}

	public void setNomeItem(String nomeItem) {
		this.nomeItem = nomeItem;
	}

	public Integer getQntItem() {
		return qntItem;
	}

	public void setQntItem(Integer qntItem) {
		this.qntItem = qntItem;
	}

	public float getValorItem() {
		return valorItem;
	}

	public void setValorItem(float valorItem) {
		this.valorItem = valorItem;
	}

	@Override
	public String toString() {
		return "TbPedidoTemItem [idItem=" + idItem + ", idPedido=" + idPedido + ", nomeItem=" + nomeItem + ", qntItem="
				+ qntItem + ", valorItem=" + valorItem + "]";
	}
	
	
}
