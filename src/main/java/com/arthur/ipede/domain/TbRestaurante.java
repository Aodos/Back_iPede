package com.arthur.ipede.domain;

import java.io.Serializable;

public class TbRestaurante implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idRestaurante;

	private String cep;

	@Override
	public String toString() {
		return "TbRestaurante [idRestaurante=" + idRestaurante + ", cep=" + cep + ", cidade=" + cidade
				+ ", cnpjRestaurante=" + cnpjRestaurante + ", endereco=" + endereco + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", nomeRestaurante=" + nomeRestaurante + ", senhaRestaurante="
				+ senhaRestaurante + "]";
	}

	private String cidade;

	private String cnpjRestaurante;

	private String endereco;

	private float latitude;

	private float longitude;

	private String nomeRestaurante;

	private String senhaRestaurante;

	public TbRestaurante() {
	}

	public int getIdRestaurante() {
		return this.idRestaurante;
	}

	public void setIdRestaurante(int idRestaurante) {
		this.idRestaurante = idRestaurante;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCnpjRestaurante() {
		return this.cnpjRestaurante;
	}

	public void setCnpjRestaurante(String cnpjRestaurante) {
		this.cnpjRestaurante = cnpjRestaurante;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public float getLatitude() {
		return this.latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return this.longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getNomeRestaurante() {
		return this.nomeRestaurante;
	}

	public void setNomeRestaurante(String nomeRestaurante) {
		this.nomeRestaurante = nomeRestaurante;
	}

	public String getSenhaRestaurante() {
		return this.senhaRestaurante;
	}

	public void setSenhaRestaurante(String senhaRestaurante) {
		this.senhaRestaurante = senhaRestaurante;
	}

	public TbRestaurante(int idRestaurante, String cep, String cidade, String cnpjRestaurante, String endereco,
			float latitude, float longitude, String nomeRestaurante, String senhaRestaurante) {
		super();
		this.idRestaurante = idRestaurante;
		this.cep = cep;
		this.cidade = cidade;
		this.cnpjRestaurante = cnpjRestaurante;
		this.endereco = endereco;
		this.latitude = latitude;
		this.longitude = longitude;
		this.nomeRestaurante = nomeRestaurante;
		this.senhaRestaurante = senhaRestaurante;
	}

}