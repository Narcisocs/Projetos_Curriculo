package br.unifor.ads.entidades;

public class Tarefa {
	Long id;
	String resumo;
	Usuario usu_resp;
	String status;
	String tempo_gasto;
	String tempo_previsto;
	Projeto projeto;
	String descricao;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getResumo() {
		return resumo;
	}
	public void setResumo(String resumo) {
		this.resumo = resumo;
	}
	public Usuario getUsu_resp() {
		return usu_resp;
	}
	public void setUsu_resp(Usuario usu_resp) {
		this.usu_resp = usu_resp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTempo_gasto() {
		return tempo_gasto;
	}
	public void setTempo_gasto(String tempo_gasto) {
		this.tempo_gasto = tempo_gasto;
	}
	public String getTempo_previsto() {
		return tempo_previsto;
	}
	public void setTempo_previsto(String tempo_previsto) {
		this.tempo_previsto = tempo_previsto;
	}
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
