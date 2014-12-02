package br.unifor.ads.entidades;

import java.io.Serializable;

public class Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9033639119663449236L;
	Long id;
	String nome;
	String email;
	String senha;
	String tipo_usuario;
	String ativo;
	boolean respProj;
	boolean respTar;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getTipo_usuario() {
		return tipo_usuario;
	}
	public void setTipo_usuario(String tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	public boolean isRespProj() {
		return respProj;
	}
	public void setRespProj(boolean respProj) {
		this.respProj = respProj;
	}
	public boolean isRespTar() {
		return respTar;
	}
	public void setRespTar(boolean respTar) {
		this.respTar = respTar;
	}
}
