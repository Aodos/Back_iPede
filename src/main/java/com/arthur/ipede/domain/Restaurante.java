package com.arthur.ipede.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Restaurante implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_restaurante;
	private String nome_restaurante;
	private String endereco;
	private String cidade;
	private String cep;
	private String longi;
	private String latitu;

	public Restaurante() {
		
	}

	public Restaurante(Integer id_restaurante, String nome_restaurante, String endereco, String cidade, String cep,
			String longi, String latitu) {
		super();
		this.id_restaurante = id_restaurante;
		this.nome_restaurante = nome_restaurante;
		this.endereco = endereco;
		this.cidade = cidade;
		this.cep = cep;
		this.longi = longi;
		this.latitu = latitu;
	}

	public Integer getId_restaurante() {
		return id_restaurante;
	}

	public void setId_restaurante(Integer id_restaurante) {
		this.id_restaurante = id_restaurante;
	}

	public String getNome_restaurante() {
		return nome_restaurante;
	}

	public void setNome_restaurante(String nome_restaurante) {
		this.nome_restaurante = nome_restaurante;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLongi() {
		return longi;
	}

	public void setLongi(String longi) {
		this.longi = longi;
	}

	public String getLatitu() {
		return latitu;
	}

	public void setLatitu(String latitu) {
		this.latitu = latitu;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_restaurante == null) ? 0 : id_restaurante.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurante other = (Restaurante) obj;
		if (id_restaurante == null) {
			if (other.id_restaurante != null)
				return false;
		} else if (!id_restaurante.equals(other.id_restaurante))
			return false;
		return true;
	}
	
	
}
