package br.unifor.ads.entidades;

import java.sql.Date;

public class BancoConhecimento {
	Long id;
	Tecnologia tecnologia;
	Usuario usu_contribuidor;
	String resumo;
	Date cadastro;
	String descricao;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Tecnologia getTecnologia() {
		return tecnologia;
	}
	public void setTecnologia(Tecnologia tecnologia) {
		this.tecnologia = tecnologia;
	}
	public Usuario getUsu_contribuidor() {
		return usu_contribuidor;
	}
	public void setUsu_contribuidor(Usuario usu_contribuidor) {
		this.usu_contribuidor = usu_contribuidor;
	}
	public String getResumo() {
		return resumo;
	}
	public void setResumo(String resumo) {
		this.resumo = resumo;
	}
	public Date getCadastro() {
		return cadastro;
	}
	public void setCadastro(Date cadastro) {
		this.cadastro = cadastro;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
