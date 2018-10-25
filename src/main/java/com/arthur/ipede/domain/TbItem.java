package com.arthur.ipede.domain;

import java.io.Serializable;

public class TbItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//idt_id_item, nme_nome_item, dsc_descricao_item, vlr_valor_item,
	//flg_disponibilidade_item, url_foto_item, fk_idt_id_restaurante
	
	private Integer idt_id_item;
	private String nme_nome_item;
	private String dsc_descricao_item;
	private float vlr_valor_item;
	private Integer flg_disponibilidade_item;
	private String url_foto_item;
	private Integer fk_idt_id_restaurante;
	
	public TbItem() {
	}

	public TbItem(Integer idt_id_item, String nme_nome_item, String dsc_descricao_item, float vlr_valor_item,
			Integer flg_disponibilidade_item, String url_foto_item, Integer fk_idt_id_restaurante) {
		super();
		this.idt_id_item = idt_id_item;
		this.nme_nome_item = nme_nome_item;
		this.dsc_descricao_item = dsc_descricao_item;
		this.vlr_valor_item = vlr_valor_item;
		this.flg_disponibilidade_item = flg_disponibilidade_item;
		this.url_foto_item = url_foto_item;
		this.fk_idt_id_restaurante = fk_idt_id_restaurante;
	}

	public Integer getIdt_id_item() {
		return idt_id_item;
	}

	public void setIdt_id_item(Integer idt_id_item) {
		this.idt_id_item = idt_id_item;
	}

	public String getNme_nome_item() {
		return nme_nome_item;
	}

	public void setNme_nome_item(String nme_nome_item) {
		this.nme_nome_item = nme_nome_item;
	}

	public String getDsc_descricao_item() {
		return dsc_descricao_item;
	}

	public void setDsc_descricao_item(String dsc_descricao_item) {
		this.dsc_descricao_item = dsc_descricao_item;
	}

	public float getVlr_valor_item() {
		return vlr_valor_item;
	}

	public void setVlr_valor_item(float vlr_valor_item) {
		this.vlr_valor_item = vlr_valor_item;
	}

	public Integer getFlg_disponibilidade_item() {
		return flg_disponibilidade_item;
	}

	public void setFlg_disponibilidade_item(Integer flg_disponibilidade_item) {
		this.flg_disponibilidade_item = flg_disponibilidade_item;
	}

	public String getUrl_foto_item() {
		return url_foto_item;
	}

	public void setUrl_foto_item(String url_foto_item) {
		this.url_foto_item = url_foto_item;
	}

	public Integer getFk_idt_id_restaurante() {
		return fk_idt_id_restaurante;
	}

	public void setFk_idt_id_restaurante(Integer fk_idt_id_restaurante) {
		this.fk_idt_id_restaurante = fk_idt_id_restaurante;
	}

	@Override
	public String toString() {
		return "TbItem [idt_id_item=" + idt_id_item + ", nme_nome_item=" + nme_nome_item + ", dsc_descricao_item="
				+ dsc_descricao_item + ", vlr_valor_item=" + vlr_valor_item + ", flg_disponibilidade_item="
				+ flg_disponibilidade_item + ", url_foto_item=" + url_foto_item + ", fk_idt_id_restaurante="
				+ fk_idt_id_restaurante + "]";
	}

	
}
