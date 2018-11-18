package com.arthur.ipede.domain;

import java.io.Serializable;

//idt_id_cliente, nme_primeiro_nome, nme_ultimo_nome, cpf_cliente,
//eml_email, cel_celular, pwd_senha, ddd_ddd, url_foto_cliente
public class TbCliente implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idt_id_cliente;
	private String nme_primeiro_nome;
	private String nme_ultimo_nome;
	private String cpf_cliente;
	private String eml_email;
	private String cel_celular;
	
	//@JsonIgnore
	private String pwd_senha;
	private Integer ddd_ddd;
	private String url_foto_cliente;
	
	public TbCliente() {
	}
	
	public TbCliente(Integer idt_id_cliente, String nme_primeiro_nome, String nme_ultimo_nome, String cpf_cliente,
			String eml_email, String cel_celular, String pwd_senha, Integer ddd_ddd, String url_foto_cliente) {
		super();
		this.idt_id_cliente = idt_id_cliente;
		this.nme_primeiro_nome = nme_primeiro_nome;
		this.nme_ultimo_nome = nme_ultimo_nome;
		this.cpf_cliente = cpf_cliente;
		this.eml_email = eml_email;
		this.cel_celular = cel_celular;
		this.pwd_senha = pwd_senha;
		this.ddd_ddd = ddd_ddd;
		this.url_foto_cliente = url_foto_cliente;
	}
	
	public TbCliente(Integer idt_id_cliente, String nme_primeiro_nome, String nme_ultimo_nome, String cpf_cliente,
			String eml_email, String cel_celular, String pwd_senha, Integer ddd_ddd) {
		super();
		this.idt_id_cliente = idt_id_cliente;
		this.nme_primeiro_nome = nme_primeiro_nome;
		this.nme_ultimo_nome = nme_ultimo_nome;
		this.cpf_cliente = cpf_cliente;
		this.eml_email = eml_email;
		this.cel_celular = cel_celular;
		this.pwd_senha = pwd_senha;
		this.ddd_ddd = ddd_ddd;
	}

	public Integer getIdt_id_cliente() {
		return idt_id_cliente;
	}

	public void setIdt_id_cliente(Integer idt_id_cliente) {
		this.idt_id_cliente = idt_id_cliente;
	}

	public String getNme_primeiro_nome() {
		return nme_primeiro_nome;
	}

	public void setNme_primeiro_nome(String nme_primeiro_nome) {
		this.nme_primeiro_nome = nme_primeiro_nome;
	}

	public String getNme_ultimo_nome() {
		return nme_ultimo_nome;
	}

	public void setNme_ultimo_nome(String nme_ultimo_nome) {
		this.nme_ultimo_nome = nme_ultimo_nome;
	}

	public String getCpf_cliente() {
		return cpf_cliente;
	}

	public void setCpf_cliente(String cpf_cliente) {
		this.cpf_cliente = cpf_cliente;
	}

	public String getEml_email() {
		return eml_email;
	}

	public void setEml_email(String eml_email) {
		this.eml_email = eml_email;
	}

	public String getCel_celular() {
		return cel_celular;
	}

	public void setCel_celular(String cel_celular) {
		this.cel_celular = cel_celular;
	}

	public String getPwd_senha() {
		return pwd_senha;
	}

	public void setPwd_senha(String pwd_senha) {
		this.pwd_senha = pwd_senha;
	}

	public Integer getDdd_ddd() {
		return ddd_ddd;
	}

	public void setDdd_ddd(Integer ddd_ddd) {
		this.ddd_ddd = ddd_ddd;
	}

	public String getUrl_foto_cliente() {
		return url_foto_cliente;
	}

	public void setUrl_foto_cliente(String url_foto_cliente) {
		this.url_foto_cliente = url_foto_cliente;
	}

	@Override
	public String toString() {
		return "TbCliente [idt_id_cliente=" + idt_id_cliente + ", nme_primeiro_nome=" + nme_primeiro_nome
				+ ", nme_ultimo_nome=" + nme_ultimo_nome + ", cpf_cliente=" + cpf_cliente + ", eml_email=" + eml_email
				+ ", cel_celular=" + cel_celular + ", pwd_senha=" + pwd_senha + ", ddd_ddd=" + ddd_ddd
				+ ", url_foto_cliente=" + url_foto_cliente + "]";
	}
	
	
	
	
}
